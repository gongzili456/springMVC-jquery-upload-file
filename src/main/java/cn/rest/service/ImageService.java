package cn.rest.service;

import java.util.List;

import cn.rest.domain.ImageFile;

public interface ImageService {

	boolean saveImage(ImageFile i);

	boolean saveImageBatch(List<ImageFile> list);

	boolean deleteImage(String name);

	ImageFile queryImage(String name);

}
