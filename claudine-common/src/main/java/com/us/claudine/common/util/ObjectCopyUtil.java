package com.us.claudine.common.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @ClassName ObjectCopyUtil
 * @Desciption 对象复制工具类
 * @Author loren
 * @Date 2020/7/1 11:21 AM
 * @Version 1.0
 **/
@Slf4j
public class ObjectCopyUtil {

	/**
	 * 复制对象
	 *
	 * @param source      源对象
	 * @param targetClazz 目标对象Class类型
	 * @return 目标对象
	 */
	public static <S, T> T copy(S source, Class<T> targetClazz) {
		if (source == null) {
			return null;
		}

		T target;
		try {
			target = targetClazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		BeanUtils.copyProperties(source, target);

		return target;
	}

	/**
	 * 复制对象集合
	 *
	 * @param sources     源对象集合
	 * @param targetClazz 目标对象Class类型
	 * @return 目标对象集合
	 */
	public static <S, T> List<T> copy(List<S> sources, Class<T> targetClazz) {
		if (CollectionUtils.isEmpty(sources)) {
			return Lists.newArrayList();
		}

		List<T> targets = Lists.newArrayList();
		sources.forEach((source) -> {
			T target = copy(source, targetClazz);
			if (target != null) {
				targets.add(target);
			}

		});
		return targets;
	}

}
