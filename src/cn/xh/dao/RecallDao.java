package cn.xh.dao;

import cn.xh.domain.*;

import java.util.List;

public interface RecallDao {

	void tempDel(Record o);

	User login(String username, String password);

	boolean register(User user);

	List<Record> getCategoryRecord(String cid);

    List<Record> rwsk();

	List<Record> sets();

	List<Record> jjjr();

	List<Record> kxjs();

	List<Record> jyks();

	void personInformation(User user);

	void personPassword(User user);

	List<Record> search(String search);

	Record findRecordById(String record_id);

	void addfavorite(String string, String user_id, String record_id);

	List<Favorite> findFavoriteByUserId(User user);

	boolean findFavorite(String user_id, String record_id);

	void delFavorite(String record_id);

	void tempDel(Order o);
}

