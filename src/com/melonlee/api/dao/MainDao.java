package com.melonlee.api.dao;import java.sql.Connection;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.SQLException;import java.sql.Statement;import java.util.ArrayList;import org.springframework.jdbc.core.JdbcTemplate;import org.springframework.jdbc.core.PreparedStatementCreator;import org.springframework.jdbc.core.RowCallbackHandler;import org.springframework.jdbc.support.GeneratedKeyHolder;import org.springframework.jdbc.support.KeyHolder;import com.melonlee.api.bean.FunctionBean;import com.melonlee.api.bean.ModuleBean;import com.melonlee.api.bean.ProjectBean;import com.melonlee.api.utils.DateFormatUtils;public class MainDao {	private JdbcTemplate jdbcTemplate;	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {		this.jdbcTemplate = jdbcTemplate;	}	// 创建项目	public Integer createPro(final String title, final String baseUrl,			final String descStr, final String version) {		KeyHolder keyHolder = new GeneratedKeyHolder();		jdbcTemplate.update(new PreparedStatementCreator() {			@Override			public PreparedStatement createPreparedStatement(Connection con)					throws SQLException {				String sql = "insert into T_Project  (title,baseUrl,descStr,createDate,version) values (?,?,?,?,?)";				PreparedStatement ps = con.prepareStatement(sql,						Statement.RETURN_GENERATED_KEYS);				ps.setString(1, title);				ps.setString(2, baseUrl);				ps.setString(3, descStr);				ps.setString(4, DateFormatUtils.getNow());				ps.setString(5, version);				return ps;			}		}, keyHolder);		return keyHolder.getKey().intValue();	}	public void updatePro(int id, String title, String baseUrl, String descStr,			String version) {		jdbcTemplate				.update("update T_Project set title = ? ,baseUrl = ? ,descStr = ?,version = ?  where id =  ?",						new Object[] { title, baseUrl, descStr, version, id });	}	public ArrayList<ProjectBean> getPros() {		final ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();		String sql = "select * from T_Project order by createDate desc";		jdbcTemplate.query(sql, new RowCallbackHandler() {			@Override			public void processRow(ResultSet result) throws SQLException {				/*				 * 组装person bean信息				 */				ProjectBean bean = new ProjectBean();				bean.setId(result.getInt("id"));				bean.setTitle(result.getString("title"));				bean.setCreateDate(result.getString("createDate"));				list.add(bean);			}		});		return list;	}	public Integer createModule(final int pID, final String title,			final String baseUrl, final String descStr) {		KeyHolder keyHolder = new GeneratedKeyHolder();		jdbcTemplate.update(new PreparedStatementCreator() {			@Override			public PreparedStatement createPreparedStatement(Connection con)					throws SQLException {				String sql = "insert into T_Module  (title,createDate,pID,baseUrl,descStr) values (?,?,?,?,?)";				PreparedStatement ps = con.prepareStatement(sql,						Statement.RETURN_GENERATED_KEYS);				ps.setString(1, title);				ps.setString(2, DateFormatUtils.getNow());				ps.setInt(3, pID);				ps.setString(4, baseUrl);				ps.setString(5, descStr);				return ps;			}		}, keyHolder);		return keyHolder.getKey().intValue();	}		public void updateModule(int id, String title, String baseUrl, String descStr) {		jdbcTemplate		.update("update T_Module set title = ? ,baseUrl = ? ,descStr = ?  where id =  ?",				new Object[] { title, baseUrl,descStr, id });			}	public ArrayList<ModuleBean> getModules(int pID) {		final ArrayList<ModuleBean> list = new ArrayList<ModuleBean>();		String sql = "select T_Module.*,T_Project.* from T_Module left join T_Project on T_Module.pID = T_Project.id  where  T_Module.pID = "				+ pID + " order by T_Module.createDate desc";		jdbcTemplate.query(sql, new RowCallbackHandler() {			@Override			public void processRow(ResultSet result) throws SQLException {				/*				 * 组装person bean信息				 */				ModuleBean bean = new ModuleBean();				bean.setId(result.getInt("T_Module.id"));				bean.setTitle(result.getString("T_Module.title"));				bean.setCreateDate(result.getString("T_Module.createDate"));				bean.setpID(result.getInt("T_Project.id"));				bean.setpName(result.getString("T_Project.title"));				list.add(bean);			}		});		return list;	}	public ModuleBean getModule(int id) {		final ModuleBean bean = new ModuleBean();		String sql = "select T_Module.*,T_Project.* from T_Module left join T_Project on T_Module.pID = T_Project.id  where  T_Module.id = "				+ id;		jdbcTemplate.query(sql, new RowCallbackHandler() {			@Override			public void processRow(ResultSet result) throws SQLException {				bean.setId(result.getInt("T_Module.id"));				bean.setTitle(result.getString("T_Module.title"));				bean.setBaseUrl(result.getString("T_Module.baseUrl"));				bean.setDescStr(result.getString("T_Module.descStr"));				bean.setCreateDate(result.getString("T_Module.createDate"));				bean.setpID(result.getInt("T_Project.id"));				bean.setpName(result.getString("T_Project.title"));			}		});		return bean;	}	public Integer saveFun(final FunctionBean bean) {		KeyHolder keyHolder = new GeneratedKeyHolder();		jdbcTemplate.update(new PreparedStatementCreator() {			@Override			public PreparedStatement createPreparedStatement(Connection con)					throws SQLException {				String sql = "insert into T_Function  (title,url,httpMethod,isLogin,params,mID,createDate) values (?,?,?,?,?,?,?)";				PreparedStatement ps = con.prepareStatement(sql,						Statement.RETURN_GENERATED_KEYS);				ps.setString(1, bean.getTitle());				ps.setString(2, bean.getUrl());				ps.setString(3, bean.getHttpMethod());				ps.setInt(4, bean.getIsLogin());				ps.setString(5, bean.getParams());				ps.setInt(6, bean.getmID());				ps.setString(7, bean.getCreateDate());				return ps;			}		}, keyHolder);		return keyHolder.getKey().intValue();	}	public ArrayList<FunctionBean> getFuns(int mID) {		final ArrayList<FunctionBean> list = new ArrayList<FunctionBean>();		String sql = "select T_Module.id,T_Module.title,T_Project.id,T_Project.title,T_Function.* from T_Module left join T_Project on T_Module.pID = T_Project.id left join T_Function on T_Module.id = T_Function.mID  where  T_Module.id = "				+ mID + " order by T_Function.createDate desc";		jdbcTemplate.query(sql, new RowCallbackHandler() {			@Override			public void processRow(ResultSet result) throws SQLException {				/*				 * 组装person bean信息				 */				FunctionBean bean = new FunctionBean();				bean.setId(result.getInt("T_Function.id"));				bean.setTitle(result.getString("T_Function.title"));				bean.setCreateDate(result.getString("T_Function.createDate"));				bean.setUrl(result.getString("T_Function.url"));				bean.setHttpMethod(result.getString("T_Function.httpMethod"));				bean.setIsLogin(result.getInt("T_Function.isLogin"));				bean.setParams(result.getString("T_Function.params"));				bean.setmID(result.getInt("T_Module.id"));				bean.setmName(result.getString("T_Module.title"));				bean.setpID(result.getInt("T_Project.id"));				bean.setpName(result.getString("T_Project.title"));				list.add(bean);			}		});		return list;	}	public ProjectBean getPro(int pID) {		final ProjectBean pro = new ProjectBean();		String sql = "select * from T_Project where id =" + pID;		jdbcTemplate.query(sql, new RowCallbackHandler() {			@Override			public void processRow(ResultSet result) throws SQLException {				/*				 * 组装person bean信息				 */				pro.setId(result.getInt("id"));				pro.setTitle(result.getString("title"));				pro.setBaseUrl(result.getString("baseUrl"));				pro.setDescStr(result.getString("descStr"));				pro.setVersion(result.getString("version"));			}		});		return pro;	}	public ArrayList<ModuleBean> getModulesByPro(int pID) {		final ArrayList<ModuleBean> list = new ArrayList<ModuleBean>();		String sql = "select T_Module.* from T_Module  where  T_Module.pID = "				+ pID + "";		jdbcTemplate.query(sql, new RowCallbackHandler() {			@Override			public void processRow(ResultSet result) throws SQLException {				ModuleBean bean = new ModuleBean();				bean.setId(result.getInt("T_Module.id"));				bean.setTitle(result.getString("T_Module.title"));				bean.setCreateDate(result.getString("T_Module.createDate"));				bean.setpID(result.getInt("T_Module.pID"));				final ArrayList<FunctionBean> func = new ArrayList<FunctionBean>();				jdbcTemplate.query(						"select T_Function.* from T_Function where mID = "								+ bean.getId(), new RowCallbackHandler() {							@Override							public void processRow(ResultSet result)									throws SQLException {								FunctionBean fun = new FunctionBean();								fun.setId(result.getInt("T_Function.id"));								fun.setTitle(result										.getString("T_Function.title"));								fun.setCreateDate(result										.getString("T_Function.createDate"));								fun.setUrl(result.getString("T_Function.url"));								fun.setHttpMethod(result										.getString("T_Function.httpMethod"));								fun.setIsLogin(result										.getInt("T_Function.isLogin"));								fun.setParams(result										.getString("T_Function.params"));								fun.setmID(result.getInt("T_Function.mID"));								func.add(fun);							}						});				bean.setFunctions(func);				list.add(bean);			}		});		return list;	}}