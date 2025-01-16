package cn.xh.service;

import cn.xh.domain.Record;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;

import java.util.List;

public interface RecallService {

	User login(String username, String password);

	boolean register(String username, String password, String name, String sex, String tel, String address);

	List<Record> getCategoryRecord(String cid);

	List<Record> rwsk();

	List<Record> sets();

	List<Record> jjjr();

	List<Record> kxjs();

	List<Record> jyks();

	void personInformation(String username, String name, String sex, String tel, String address);

	void personPassword(String password, String username);

	List<Record> search(String search);

	Record findRecordById(String record_id);

	void addfavorite(String user_id, String record_id);

	List<Favorite> findFavoriteByUserId(User user);

	boolean findFavorite(String user_id, String record_id);

	void delFavorite(String record_id);


	void tempDel(Record records);
}

