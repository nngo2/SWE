
package business.productsubsystem;

import business.externalinterfaces.IProductFromDb;
import business.externalinterfaces.IProductFromGui;


/**
 * @author  
 */
class Product implements IProductFromDb, IProductFromGui {

    private Integer productId;
    private String productName;
    private String quantityAvail;
    private String unitPrice;
    private String mfgDate;
    private Integer catalogId;
    private String description;
    
    //this is the usual constructor, obtained when reading a product from the db
    Product(Integer pi, String pn, String qa, String up, String md, Integer ci, String d){
        productId=pi;
        productName = pn;
        quantityAvail = qa;
        unitPrice = up;
        mfgDate = md;
        catalogId = ci;
        description = d;
    }
    //this constructor is used when getting user-entered data in adding a new product
    Product(String name, String date, String numAvail, String price){
    	this(null, name, numAvail, price, date, null, null);
    }
    /**
	 * @return  Returns the catalogId.
	 * @uml.property  name="catalogId"
	 */
    public Integer getCatalogId() {
        return catalogId;
    }
    /**
	 * @return  Returns the mfgDate.
	 * @uml.property  name="mfgDate"
	 */
    public String getMfgDate() {
        return mfgDate;
    }
    /**
	 * @return  Returns the productId.
	 * @uml.property  name="productId"
	 */
    public Integer getProductId() {
        return productId;
    }
    /**
	 * @return  Returns the productName.
	 * @uml.property  name="productName"
	 */
    public String getProductName() {
        return productName;
    }
    /**
	 * @return  Returns the quantityAvail.
	 * @uml.property  name="quantityAvail"
	 */
    public String getQuantityAvail() {
        return quantityAvail;
    }
    /**
	 * @return  Returns the unitPrice.
	 * @uml.property  name="unitPrice"
	 */
    public String getUnitPrice() {
        return unitPrice;
    }
    /**
	 * @return  Returns the description.
	 * @uml.property  name="description"
	 */
    public String getDescription() {
        return description;
    }
	/** 
	 * @uml.property name="catalog"
	 * @uml.associationEnd inverse="product:business.productsubsystem.Catalog"
	 * @uml.association name="has"
	 */
	private Catalog catalog;

	/** 
	 * Getter of the property <tt>catalog</tt>
	 * @return  Returns the catalog.
	 * @uml.property  name="catalog"
	 */
	public Catalog getCatalog() {
		return catalog;
	}
	/** 
	 * Setter of the property <tt>catalog</tt>
	 * @param catalog  The catalog to set.
	 * @uml.property  name="catalog"
	 */
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
}
