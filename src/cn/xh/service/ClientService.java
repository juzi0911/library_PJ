package cn.xh.service;

import java.util.List;

import cn.xh.domain.Book;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;

public interface ClientService {

	User login(String username, String password);

	boolean register(String username, String password, String name, String sex, String tel, String address);

	List<Book> getCategoryBook(String cid);

	List<Book> rwsk();

	List<Book> sets();

	List<Book> jjjr();

	List<Book> kxjs();

	List<Book> jyks();

	void personInformation(String username, String name, String sex, String tel, String address);

	void personPassword(String password, String username);

	List<Book> search(String search);

	Book findBookById(String book_id);

	void addfavorite(String user_id, String book_id);

	List<Favorite> findFavoriteByUserId(User user);

	boolean findFavorite(String user_id, String book_id);

	void delFavorite(String book_id);


}
