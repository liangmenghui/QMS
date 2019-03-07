package com.unind.base.data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.CommonEntity;
import com.unind.base.domain.admin.IdEntity;
import com.unind.base.domain.admin.TreeEntity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DataHelper {

	public static Datagrid convertToDatagrid(List list, long total) {
		return new Datagrid(list, (int) total);
	}

	public static Datagrid convertToDatagrid(List list, int total) {
		return new Datagrid(list, total);
	}

	/**
	 * list转换easyui的tree json格式
	 * @param dataList		list数组
	 * @param root			根节点属性名称
	 * @param id			根节点的值
	 * @param text			要显示的属性
	 * @param cls			实体类class
	 * @return
	 */
	public static List<Map<String, Object>> convertListToTreeData(List dataList, Long root, String id, String text, Class cls) {
		return convertListToTreeData("getPkParent", dataList, root, id, text, cls);
	}

	/**
	 * list转换easyui的tree json格式
	 * @param parentMethod	获取上级节点的方法名称
	 * @param dataList		list数组
	 * @param root			根节点属性名称
	 * @param id			根节点的值
	 * @param text			要显示的属性
	 * @param cls			实体类class
	 * @return
	 */
	public static List<Map<String, Object>> convertListToTreeData(String parentMethod, List dataList, Long root, String id, String text, Class cls) {
		if (null == dataList || dataList.size()==0) {
	      return new ArrayList();
	    }
		Map<String, String> attrMap = new HashMap();
		attrMap.put("id", id);
		attrMap.put("text", text);
//	    attrMap.put("iconCls", "bsIconCls");
	    List<Map<String, Object>> rootList = getParentList(parentMethod, dataList, root, attrMap, cls, null);
	    return rootList;
	}

	protected static List<Map<String, Object>> getParentList(String parentMethod, List dataList, Long root, Map<String, String> attrMap, Class cls) {
		List<Map<String, Object>> rootList = new ArrayList<Map<String, Object>>();
		Object object = null;
		try {
			Long id, pid;
			String text;
			Map<String, Object> m = null;
			Field f = null;
			Class c = null;
			for (int i = 0; i < dataList.size(); i++) {
				object = dataList.get(i);
				c = TreeEntity.class;
				pid = (Long) c.getDeclaredMethod(parentMethod, new Class[0]).invoke(object, new Object[0]);
				if (((null==root || null==pid) && root!=pid) || (null!=root && null!=pid && root.longValue()!=pid.longValue())) {
					continue;
				}
//				if(null!=cls.getSuperclass() && cls.getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass();
//				}else if(null!=cls.getSuperclass().getSuperclass() && cls.getSuperclass().getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass().getSuperclass();
//				}else if(null!=cls.getSuperclass().getSuperclass().getSuperclass() && cls.getSuperclass().getSuperclass().getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass().getSuperclass().getSuperclass();
//				}else if(null!=cls.getSuperclass().getSuperclass().getSuperclass().getSuperclass() && cls.getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass().getSuperclass().getSuperclass().getSuperclass();
//				}else {
//					throw new IllegalArgumentException();
//				}

				c = IdEntity.class;
				f = c.getDeclaredField(attrMap.get("id"));
				f.setAccessible(true);
				id = (Long) f.get(object);

				c = CommonEntity.class;
				f = c.getDeclaredField(attrMap.get("text"));
				f.setAccessible(true);
				text = (String)  f.get(object);

				m = new LinkedHashMap<String, Object>();
				//ID
				m.put("id", id);
				m.put("title", text);
				m.put("attributes", object);
				List<Map<String, Object>> childrenList = getParentList(parentMethod, dataList, id, attrMap, cls);
				if(null!=childrenList && childrenList.size() > 0) {
					m.put("children", childrenList);
					m.put("expand", true);
				}else {
					m.put("expand", true);
				}
				rootList.add(m);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return rootList;
	}

	protected static List<Map<String, Object>> getParentList(String parentMethod, List dataList, Long root, Map<String, String> attrMap, Class cls, Object obj) {
		List<Map<String, Object>> rootList = new ArrayList<Map<String, Object>>();
		Object object = null;
		try {
			Long id, pid;
			String text;
			Map<String, Object> m = null;
			Field f = null;
			Class c = null;
			for (int i = 0; i < dataList.size(); i++) {
				object = dataList.get(i);
				c = TreeEntity.class;
				pid = (Long) c.getDeclaredMethod(parentMethod, new Class[0]).invoke(object, new Object[0]);
				if (((null==root || null==pid) && root!=pid) || (null!=root && null!=pid && root.longValue()!=pid.longValue())) {
					continue;
				}
//				if(null!=cls.getSuperclass() && cls.getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass();
//				}else if(null!=cls.getSuperclass().getSuperclass() && cls.getSuperclass().getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass().getSuperclass();
//				}else if(null!=cls.getSuperclass().getSuperclass().getSuperclass() && cls.getSuperclass().getSuperclass().getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass().getSuperclass().getSuperclass();
//				}else if(null!=cls.getSuperclass().getSuperclass().getSuperclass().getSuperclass() && cls.getSuperclass().getSuperclass().getSuperclass().getSuperclass().getName().equals(AbstractPersistable.class.getName())) {
//					c = cls.getSuperclass().getSuperclass().getSuperclass().getSuperclass();
//				}else {
//					throw new IllegalArgumentException();
//				}

				c = IdEntity.class;
				f = c.getDeclaredField(attrMap.get("id"));
				f.setAccessible(true);
				id = (Long) f.get(object);

				c = CommonEntity.class;
				f = c.getDeclaredField(attrMap.get("text"));
				f.setAccessible(true);
				text = (String)  f.get(object);

				m = new LinkedHashMap<String, Object>();
				//ID
				m.put("id", id);
				m.put("title", text);
				m.put("attributes", object);
				if(null!=obj && obj instanceof CommonEntity) {
					CommonEntity e = (CommonEntity) obj;
					Map<String, Object> pMap = new HashMap<String, Object>();
					pMap.put("id", e.getId());
					pMap.put("title", e.getBsName());
					m.put("parent", pMap);
				}
				List<Map<String, Object>> childrenList = getParentList(parentMethod, dataList, id, attrMap, cls, object);
				if(null!=childrenList && childrenList.size() > 0) {
					m.put("children", childrenList);
					m.put("expand", true);
				}else {
					m.put("expand", true);
				}
				rootList.add(m);
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return rootList;
	}

	/*public static void main(String[] args) {
		List<SysResrce> list = new ArrayList<SysResrce>();
		int index = 0;
		for (int i = 0; i < 3; i++) {
			index = (i+1);
			SysResrce resrce = new SysResrce();
			resrce.setBsCode("大类-编码-"+index);
			resrce.setBsName("大类-名称-"+index);
			resrce.setBsComment("大类-备注");
			resrce.setBsLeaf(0);
			resrce.setBsLevel(1);
			resrce.setId((long)index);
			resrce.setBsSortNo(index);
			list.add(resrce);
		}

		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				index += (j+1);
				SysResrce resrce = new SysResrce();
				resrce.setBsCode("小类-编码-1");
				resrce.setBsName("小类-名称-1");
				resrce.setBsComment("小类-备注");
				resrce.setBsLeaf(0);
				resrce.setBsLevel(2);
				resrce.setId((long)index);
				resrce.setBsSortNo(j);
				resrce.setPkParent((long)(i+1));
				list.add(resrce);
			}
		}

		for (int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 2; k++) {
					index += (k+1);
					SysResrce resrce = new SysResrce();
					resrce.setBsCode("小类-编码-1");
					resrce.setBsName("小类-名称-1");
					resrce.setBsComment("小类-备注");
					resrce.setBsLeaf(1);
					resrce.setBsLevel(3);
					resrce.setId((long)index);
					resrce.setBsSortNo(k);
					resrce.setPkParent((long)(i+1) + (long)(k+1));
					list.add(resrce);
				}
			}
		}
		List<Map<String, Object>> lists =convertListToTreeData(list, null, "id", "bsName", SysResrce.class);
		System.out.println(lists);
	}*/
}
