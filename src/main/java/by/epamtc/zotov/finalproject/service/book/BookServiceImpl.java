package by.epamtc.zotov.finalproject.service.book;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.dao.book.BookDAO;
import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class BookServiceImpl implements BookService {
    private static final String IMG_PATH = "D:\\PROGRAMEZ\\Java\\Apache\\apache-tomcat-9.0.50\\resources\\library\\";
    private static final String WORD_PATTERN = "[,\\s]+";

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        List<Book> books = null;

        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        try {
            books = bookDAO.getAllBooks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return books;
    }

    @Override
    public Book findBookById(int targetId) throws ServiceException {
        Book target = null;

        try {
            target = DAOFactory.getInstance().getBookDAO().findBookById(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return target;
    }

    @Override
    public List<Book> findBooksByTitle(String title) throws ServiceException {
        List<Book> books = getAllBooks();
        List<Book> foundBooks = new ArrayList<>();
        String targetTitle = title.toUpperCase();

        for (Book book : books) {
            String bookTitle = book.getTitle().toUpperCase();
            if (bookTitle.contains(targetTitle)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws ServiceException {
        List<Book> books = getAllBooks();
        List<Book> foundBooks = new ArrayList<>();
        String targetAuthor = author.toUpperCase();

        for (Book book : books) {
            String bookTitle = book.getAuthor().toUpperCase();
            if (bookTitle.contains(targetAuthor)) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    @Override
    public List<Book> findBooksByTags(String tags) throws ServiceException {
        List<Book> books = getAllBooks();
        List<Book> foundBooks = new ArrayList<>();
        String[] targetTags = tags.toUpperCase().split(WORD_PATTERN);

        for (Book book : books) {
            String[] bookTags = book.getTags().split(WORD_PATTERN);
            boolean isSuitable = true;

            for (String targetTag : targetTags) {
                boolean isContained = false;

                for (String bookTag : bookTags) {
                    if (bookTag.toUpperCase().contains(targetTag)) {
                        isContained = true;
                        break;
                    }
                }
                if (isContained == false) {
                    isSuitable = false;
                    break;
                }
            }
            if (isSuitable) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    @Override
    public boolean addBook(String title, String author, String blurb, int amount, String tags, Part cover)
            throws ServiceException {
        boolean isSuccesfull = false;
        InputStream stream = null;

        String[] splitTags = tags.toUpperCase().split(WORD_PATTERN);
        StringBuffer tagString = new StringBuffer();

        for (String tag : splitTags) {
            tagString.append(tag);
            tagString.append(' ');
        }

        try {
            synchronized (BookServiceImpl.class) {
                File coverImg = new File(IMG_PATH, cover.getSubmittedFileName());
                stream = cover.getInputStream();

                if (stream != null && ImageIO.read(stream) != null) {
                    stream = cover.getInputStream();
                    Files.copy(stream, coverImg.toPath());
                    Book book = new Book(author, blurb, title, cover.getSubmittedFileName(),
                            tagString.toString().trim(), amount);
                    isSuccesfull = DAOFactory.getInstance().getBookDAO().addBook(book);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while adding book", e);
        } catch (IOException e) {
            throw new ServiceException("Exception while writing cover to files", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }

        return isSuccesfull;
    }

    @Override
    public boolean updateBook(int targetId, String title, String author, String blurb, int amount, String tags,
            String originalCover, Part cover) throws ServiceException {
        boolean isSuccesfull = false;
        Book book = null;
        InputStream stream = null;

        String[] splitTags = tags.toUpperCase().split(WORD_PATTERN);
        StringBuffer tagString = new StringBuffer();

        for (String tag : splitTags) {
            tagString.append(tag);
            tagString.append(' ');
        }

        try {
            File coverImg = new File(IMG_PATH, cover.getSubmittedFileName());
            stream = cover.getInputStream();

            if (cover.getSubmittedFileName().isEmpty()) {
                book = new Book(targetId, author, blurb, title, originalCover, tagString.toString().trim(), amount);
            } else {
                synchronized (BookServiceImpl.class) {
                    if (ImageIO.read(stream) != null) {
                        stream = cover.getInputStream();
                        File oldCoverImg = new File(IMG_PATH, originalCover);
                        oldCoverImg.delete();

                        Files.copy(stream, coverImg.toPath());

                        book = new Book(targetId, author, blurb, title, cover.getSubmittedFileName(),
                                tagString.toString().trim(), amount);
                    }
                }
            }

            isSuccesfull = DAOFactory.getInstance().getBookDAO().updateBook(book);
        } catch (DAOException e) {
            throw new ServiceException("Exception while adding book", e);
        } catch (IOException e) {
            throw new ServiceException("Exception while writing cover to files", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }

        return isSuccesfull;
    }

    @Override
    public int findAvailable(int targetId) throws ServiceException {
        int available = 0;

        try {
            available = DAOFactory.getInstance().getBookDAO().findBookById(targetId).getAmount()
                    - DAOFactory.getInstance().getLoanDAO().findLeasedBooksNumber(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return available;
    }
}