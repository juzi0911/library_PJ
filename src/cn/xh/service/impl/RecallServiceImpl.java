package cn.xh.service.impl;

import cn.xh.dao.RecallDao;
import cn.xh.dao.impl.RecallDaoImpl;
import cn.xh.domain.Order;
import cn.xh.domain.Record;
import cn.xh.domain.Favorite;
import cn.xh.domain.User;
import cn.xh.service.RecallService;

import java.util.List;
import java.util.UUID;

public class RecallServiceImpl implements RecallService {

	private final RecallDao dao = new RecallDaoImpl();

	@Override
	public void tempDel(Record o) {
		dao.tempDel(o);
	}

	@Override
	public User login(String username, String password) {
		return dao.login(username, password);
	}

	@Override
	public boolean register(String username, String password, String name, String sex, String tel, String address) {
		User user = new User(UUID.randomUUID().toString(), username, password, name, sex, tel, address);
		return dao.register(user);
	}

	@Override
	public List<Record> getCategoryRecord(String cid) {
		return dao.getCategoryRecord(cid);
	}

	@Override
	public List<Record> rwsk() {
		return dao.rwsk();
	}

	@Override
	public List<Record> sets() {
		return dao.sets();
	}

	@Override
	public List<Record> jjjr() {
		return dao.jjjr();
	}

	@Override
	public List<Record> kxjs() {
		return dao.kxjs();
	}

	@Override
	public List<Record> jyks() {
		return dao.jyks();
	}

	@Override
	public void personInformation(String username, String name, String sex, String tel, String address) {
		User user = new User(null, username, null, name, sex, tel, address);
		dao.personInformation(user);
	}

	@Override
	public void personPassword(String password, String username) {
		User user = new User(null, username, password, null, null, null, null);
		dao.personPassword(user);
	}

	@Override
	public List<Record> search(String search) {
		return dao.search(search);
	}

	@Override
	public Record findRecordById(String record_id) {
		return dao.findRecordById(record_id);
	}

	@Override
	public void addfavorite(String user_id, String record_id) {
		dao.addfavorite(UUID.randomUUID().toString(), user_id, record_id);
	}

	@Override
	public List<Favorite> findFavoriteByUserId(User user) {
		return dao.findFavoriteByUserId(user);
	}

	@Override
	public boolean findFavorite(String user_id, String record_id) {
		return dao.findFavorite(user_id, record_id);
	}

	@Override
	public void delFavorite(String record_id) {
		dao.delFavorite(record_id);

	}

}

