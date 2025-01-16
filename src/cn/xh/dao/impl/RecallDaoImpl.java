package cn.xh.dao.impl;

import cn.xh.dao.RecallDao;
import cn.xh.domain.*;
import cn.xh.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class RecallDaoImpl implements RecallDao {

	Connection connection = JDBCUtil.getConnection();

	@Override
	public void tempDel(Record o) {
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(
					"insert into record(record_id,record_word,record_time) values (?,?,?)");
			prepareStatement.setString(1, o.getRecord_id());
			prepareStatement.setString(2, o.getRecord_word());
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String s=df.format(new Date());
			prepareStatement.setString(3, s);
			prepareStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ???
	@Override
	public User login(String username, String password) {
		User user = new User();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from user where user_username=? and user_password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet executeQuery = preparedStatement.executeQuery();
			if (executeQuery.next()) {
				user.setUsername(executeQuery.getString("user_username"));
				user.setPassword(executeQuery.getString("user_password"));
				user.setName(executeQuery.getString("user_name"));
				user.setSex(executeQuery.getString("user_sex"));
				user.setTel(executeQuery.getString("user_tel"));
				user.setAddress(executeQuery.getString("user_address"));
				user.setId(executeQuery.getString("user_id"));
			} else {
			}
		} catch (Exception e) {
		}
		return user;
	}

	// ???
	@Override
	public boolean register(User user) {
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from user where user_username='" + user.getUsername() + "'");
			if (resultSet.next() == true) {
				return true;
			} else {
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(
							"insert into user (user_id,user_username,user_password,user_name,user_sex,user_tel,user_address) values(?,?,?,?,?,?,?)");
					preparedStatement.setString(1, user.getId());
					preparedStatement.setString(2, user.getUsername());
					preparedStatement.setString(3, user.getPassword());
					preparedStatement.setString(4, user.getName());
					preparedStatement.setString(5, user.getSex());
					preparedStatement.setString(6, user.getTel());
					preparedStatement.setString(7, user.getAddress());

					// ??
					preparedStatement.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Record> getCategoryRecord(String cid) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = null;

			if (cid==null){
				preparedStatement=  connection.prepareStatement(
						"select * from record");
			}else {
				preparedStatement =  connection.prepareStatement(
						"select * from record");
			}

			ResultSet rs = preparedStatement.executeQuery();

			List<Record> list = new ArrayList<Record>();
			while (rs.next()) {
				Record records = new Record();
				records.setRecord_id(rs.getString("record_id"));
				records.setRecord_word(rs.getString("record_word"));
				records.setRecord_time(rs.getString("record_time"));
				list.add(records);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("?????????");
	}



	@Override
	public List<Record> rwsk() {
		return null;
	}

	@Override
	public List<Record> sets() {
		return null;
	}

	@Override
	public List<Record> jjjr() {
		return null;
	}

	@Override
	public List<Record> kxjs() {
		return null;
	}

	@Override
	public List<Record> jyks() {
		return null;
	}

	@Override
	public void personInformation(User user) {

	}

	@Override
	public void personPassword(User user) {

	}

	@Override
	public List<Record> search(String search) {
		List<Record> lists = new ArrayList<Record>();
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from record where record_word like ? or record_id like ?");
			preparedStatement.setString(1, "%" + search + "%");
			preparedStatement.setString(2, "%" + search + "%");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Record records = new Record();
				records.setRecord_id(rs.getString("record_id"));
				records.setRecord_word(rs.getString("record_word"));
				records.setRecord_time(rs.getString("record_time"));
				lists.add(records);
			}
			return lists;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public Record findRecordById(String record_id) {
		return null;
	}


	// ???????
	@Override
	public void addfavorite(String favorite_id, String user_id, String record_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("insert into favorite (favorite_id,user_id,record_id) values (?,?,?)");
			prepareStatement.setString(1, favorite_id);
			prepareStatement.setString(2, user_id);
			prepareStatement.setString(3, record_id);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ???????
	@Override
	public List<Favorite> findFavoriteByUserId(User user) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from favorite where user_id = ?");
			prepareStatement.setString(1, user.getId());
			ResultSet rs = prepareStatement.executeQuery();
			List<Favorite> list = new ArrayList<Favorite>();
			while (rs.next()) {
				Favorite favorite = new Favorite();
				favorite.setUser(user);
				favorite.setFavorite_id(rs.getString("favorite_id"));
				Record record = findRecordById(rs.getString("record_id"));
				/*favorite.setRecord(record);*/
				list.add(favorite);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean findFavorite(String user_id, String record_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from favorite where user_id=? and record_id=?");
			prepareStatement.setString(1, user_id);
			prepareStatement.setString(2, record_id);
			ResultSet rs = prepareStatement.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}

	@Override
	public void delFavorite(String record_id) {
		try {
			Connection connection = JDBCUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("delete from favorite where record_id=?");
			prepareStatement.setString(1, record_id);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tempDel(Order o) {

	}

}

