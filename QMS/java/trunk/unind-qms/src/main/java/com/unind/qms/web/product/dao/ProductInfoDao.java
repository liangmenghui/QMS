package com.unind.qms.web.product.dao;

import com.unind.qms.web.product.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 
 * @author chen
 *
 */
public interface ProductInfoDao extends CrudRepository<ProductInfo, Long>, JpaSpecificationExecutor<ProductInfo> {

//    public int countByItemNumAndVendorNumAndOrganizationId(String itemNum, String vendorNum, Long organizationId);
//
////    public List<ProductInfo> findByItemNum(String itemNum);
//
//    public List<ProductInfo> findByBsIsDelAndVendorNum(Integer bsIsDel, String vendorNum);
//
//    public List<ProductInfo> findByItemNumAndVendorNumAndBsIsDelOrderByIdDesc(String itemNum, String vendorNum, int bsIsDel);
//
//    @Modifying
//	@Query(value = "select t.id from erp_product_info t where t.item_desc like '%'||?1||'%'",nativeQuery = true)
//	public List findByItemDescLike(String itemDesc);

	public List<ProductInfo> findByBsPrCodeAndBsSuppCodeAndBsOrganizationId(String bsPrCode, String bsSuppCode, String bsOrganizationId);

	public int countByBsPrCodeAndBsSuppCodeAndBsOrganizationId(String bsPrCode, String bsSuppCode, String bsOrganizationId);

	public List<ProductInfo> findByBsIsDelAndBsPrCode(Integer bsIsDel, String bsPrCode);

	public List<ProductInfo> findByBsIsDelAndBsSuppCode(Integer bsIsDel, String bsSuppCode);

	public List<ProductInfo> findByBsPrCodeAndBsSuppCodeAndBsIsDelOrderByIdDesc(String bsPrCode,String bsSuppCode, int BsIsDel);

	public List<ProductInfo> findByBsIsDel(Integer bsIsDel);

	@Modifying
	@Query(value = "select t.id from "+ProductInfo.TABLE_NAME+" t where t.bs_pr_name like '%'||?1||'%'",nativeQuery = true)
	public List findByPrNameLike(String bsPrName);

	@Modifying
	@Query("update ProductInfo t set t.bsProcessRecordId=?1,t.bsIsApprove='1' where t.id=?2")
	public void updatebsProcessRecordIdById(Long bsProcessRecordId,Long id);

	@Modifying
	@Query("update ProductInfo t set t.bsProductRecordId=?1,t.bsIsApprove='1' where t.id=?2")
	public void updatebsProductRecordIdById(Long bsProductRecordId,Long id);

	@Modifying
	@Query("update ProductInfo t set t.bsIsApprove='0' where t.id=?1")
	public void updatebsIsApproveById(Long id);

	public int countByBsSqeAndBsIsDel(String bsSqe, int bsIsDel);

//	@Modifying
//	@Query("update ProductInfo t set t.bsStatus=?2 where t.id=?1")
//	public void updatebsStatusById(Long id,int bsStatus);
}
