def sqlInsertcnvd(db, cur, cn_url, cn_title, pub_date, hazard_level,
                              cn_impact, cnvd_id, cve_id, cn_types, cn_describe, cn_reference, cn_solution, cn_patch):
    sqlQuery = """ insert ignore into cnvd20231029(cn_url, cn_title, pub_date, hazard_level, 
                              cn_impact, cnvd_id, cve_id, cn_types, cn_describe, cn_reference, cn_solution, cn_patch) 
                              values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"""
    values = (cn_url, cn_title, pub_date, hazard_level,
                              cn_impact, cnvd_id, cve_id, cn_types, cn_describe, cn_reference, cn_solution, cn_patch)
    cur.execute(sqlQuery, values)
    db.commit()
    print(f'*{cn_title}*插入成功！')