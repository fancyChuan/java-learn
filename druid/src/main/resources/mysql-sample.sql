SELECT
			 a.SITENAME,
			 CUSTNAME,
			 CONTRACTNO,
			 CASE single_Premiums WHEN 1 THEN '是' ELSE '否' END AS single_Premiums,
			 case when ISDIRECT = 1 and (directOrgName like '直销业务%' or directOrgName like '%直销一部%' or directOrgName like '%深圳直销%') then '二部深圳直销'
     when ISDIRECT = 1 and (directOrgName like '%直销二部%' or directOrgName like '%合肥直销%') then '二部合肥直销'
		 when ISDIRECT = 1 and directOrgName not like '直销业务%' and directOrgName not like '%直销一部%' and directOrgName not like '%深圳直销%' and directOrgName not like '%直销二部%' and directOrgName not like '%合肥直销%' then '直销部'
     when ISDIRECT = 2 then '呼叫中心'
     when ISDIRECT = 3 then '渠道部'
else '-' end AS ISDIRECT,
			 SOURCES,
			 LOANTYPE,
			 DATE_FORMAT(LOANDATE, '%Y-%m-%d') LOANDATE,
			 DATE_FORMAT(withdrawDate, '%Y-%m-%d') AS withdrawDate,
			 SHOULDCAPITAL,
			 DATE_FORMAT(RECENTPAYDATE, '%Y-%m-%d') RECENTPAYDATE,
			 SHOULDAMT,
			 CURRENTDUEDAYS,
			 OVERDUEPAYMENT,
			 COMPOSITEFEES,
			 SHOULDDUEAMT,
			 RETURNAMT,
			 NORETURNAMT,
			 QYBZJ,
			 IFNULL(ROUND(actfxbzf,2),0.00) AS actfxbzf,
			 counselingFees,
			 OVERDUECAPITAL,
			 PHONENO,
			 BANK,
			 BANKNO,
			 URGEMAN,
		CASE SITUATION WHEN 1 THEN '回访处理中' WHEN 2 THEN '已卖车未结清' WHEN 3 THEN '已核销坏账' WHEN 4 THEN '已收车（收车入库）' WHEN 5 THEN '人车消失' WHEN 6 THEN '诉讼中' WHEN 7 THEN '电访中' WHEN 8 THEN '回访跟进' WHEN 9 THEN '回访交通工具核销50%' ELSE '其他' END AS SITUATION,
			 CASE CUSTSTATUS WHEN 1 THEN	'未失联，GPS不在线' WHEN 2 THEN '未失联，GPS在线' WHEN 3 THEN	'失联，GPS不在线'
			   WHEN 4 THEN	'失联，GPS在线' WHEN 5 THEN	'已核销坏账' WHEN 6 THEN	'押车' WHEN 7 THEN	'赎车' WHEN 8 THEN	'反押车'
			   WHEN 9 THEN '过户到贷后' WHEN 10 THEN	'催收用车核销50%' END AS CARSTATUS,
			 CASE WHEN CARSTATUS =1 THEN '在库' WHEN CARSTATUS =2 THEN '不在库' ELSE '未知'
			   END AS INSTATUS,
			 c.STATUS,
			 OVERDUEINT,
			 IDCARD,
			 TOTALPHASES,
			 CASE WHEN HKFS = 1 THEN	'先息后本' WHEN HKFS = 2 THEN	'等本等息' WHEN HKFS = 3 THEN	'一次性还款'
			   WHEN HKFS = 4 THEN '等额本息' WHEN HKFS = 5 THEN	'利随本清' END AS HKFS,
			 PAYPHASES,
			 CARNO AS CARNO,
			 SALESMAN,
			 TEAMLEADER,
			 AUDITAMT,
			 unifiedcustname,
			 unifiedcontractno,
			 fileNo,
			IFNULL(c.ORGNAME,'-') as ORGNAME,
			  CASE
			    WHEN microStoreFlag = 1
			    THEN '是'
			    WHEN microStoreFlag = 0
			    THEN '否'
			    ELSE '-'
			  END AS microStoreFlag,
			  coopName,
		     infoServiceFee,
		      corpName,
		agentName,
		agentCode,
		inviterDuties,
		empNo,
              contractBody,
			  usertype AS 'userType',
			  productName AS 'productName',
			  loanChannel loanChannel,
			  returnCoopName returnCoopName,
			  b.orgname departmentName,
			  c.dkfs,
			  cyssitename
			FROM
				tb_currentdue_rpt c
			left join tb_site_rpt a on c.SITECODE=a.SITECODE
			left join tb_org_rpt b on c.departmentID=b.id
			left join tpm_dictionary_cfg d on c.coopName=d.dataName and d.pid='268'
			WHERE SHOULDAMT > 0
			and c.productName not like '%股票%'
;