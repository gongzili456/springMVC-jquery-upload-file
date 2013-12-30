package cn.rest.dao;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cn.rest.domain.ImageFile;

@Component
public class ImageDaoImpl implements ImageDao {

	@Autowired
	JdbcTemplate jt;

	@Override
	public boolean saveImage(ImageFile i) {
		String sql = "INSERT INTO image(name, contentType, size, width, height, ratio, url, date, path) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int result = jt.update(sql, i.getName(), i.getContentType(),
				i.getSize(), i.getWidth(), i.getHeight(), i.getRatio(),
				i.getUrl(), i.getDate(), i.getPath());
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteImage(String name) {
		String sql = "DELETE FROM image WHERE name = ?";
		int result = jt.update(sql, name);
		return result > 0 ? true : false;
	}

	@Override
	public ImageFile queryImage(String name) {
		String sql = "select * from image where name = ?";
		List<ImageFile> is = jt.query(sql,
				new BeanPropertyRowMapper<ImageFile>(ImageFile.class), name);
		if (!is.isEmpty()) {
			return is.get(0);
		}
		return null;
	}

	@Override
	public boolean saveImageBatch(List<ImageFile> list) {
		String sql = "INSERT INTO image(name, contentType, size, width, height, ratio, url, date, path) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object[]> args = new LinkedList<Object[]>();
		for (ImageFile i : list) {
			Object[] o = new Object[] { i.getName(), i.getContentType(),
					i.getSize(), i.getWidth(), i.getHeight(), i.getRatio(),
					i.getUrl(), i.getDate(), i.getPath() };
			args.add(o);
		}

		jt.batchUpdate(sql, args);
		return true;
	}

}
