import os
import xml.etree.ElementTree as ET
import xml.dom.minidom

import pymysql as pymysql
# import scrapy
import sql


def savedb(result_id, img_url, path1_probability, traget_1, path_1, path2_probability, traget_2, path_2,
           path3_probability, traget_3, path_3):
    DBHOST = 'localhost'
    DBUSER = 'root'
    DBPASS = 'root'
    DBNAME = 'attackprediction'
    db = pymysql.connect(host=DBHOST, user=DBUSER, password=DBPASS, database=DBNAME)

    try:
        db = pymysql.connect(host=DBHOST, user=DBUSER, password=DBPASS, database=DBNAME)
        print('数据库连接成功!')
        # 这里创建游标
        cur = db.cursor()
        # 执行 SQL 语句（插入一条数据）
        sql = "INSERT INTO `tab_result` (`result_id`, `img_url`, `path1_probability`, `target_1`, `path_1`, `path2_probability`, `target_2`, `path_2`, `path3_probability`, `target_3`,`path_3`) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
        # sql.sqlInsertcnvd(db, cur)
        params = (
            result_id, img_url, str(path1_probability*100)[:5]+'%', traget_1, path_1, str(path2_probability*100)[:5]+'%', traget_2, path_2,
            str(path3_probability*100)[:5]+'%',
            traget_3, path_3)
        cur.execute(sql, params)
        # 提交更改
        db.commit()
    except pymysql.Error as e:
        print("数据插入失败：" + str(e))
        # 如果数据插入失败的话 就事件回滚
        db.rollback()
