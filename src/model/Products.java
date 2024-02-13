package model;


public class Products {
	private String productCode, productName, productLine, productScale, productVendor, productDescription;
	private int quantityInStock;
	private float buyPrice, MSRP;	
	
	/**
     * Product object is made through the parameters stored in the database. VURDER Å SLØYFE
     *
     * @param productCode        Unique identifier given to each product.
     * @param productName        Name of product.
     * @param productLine        Type of productline (Car, bus etc.)
     * @param productScale       Scale of miniature compared to real life.
     * @param productVendor      The product vendor of the miniature.
     * @param productDescription Description of the miniature.
     * @param quantityInStock    Quantity in stock.
     * @param buyPrice           Listed price for product.
     * @param MSRP               Manufacturer's Suggested Retail Price.
     */
	
	public Products (String productCode, String productName, String productLine, String productScale, String productVendor,
			String productDescription, int quantityInStock, float buyPrice, float MSRP) {
		
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock =  quantityInStock;
		this.buyPrice = buyPrice;
		this.MSRP = MSRP;
	}
	
    /**
     * Getter for product code String.
     *
     * @return Product code as a String.
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Setter for the product code String.
     *
     * @param Desired productCode.
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * Getter for product name String.
     *
     * @return Product name as a String
     */
    public String getProductName() {
        return productName;
    }
    
   
    /**
     * Setter for the product name String.
     *
     * @param Desired productName.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Getter for product line String.
     *
     * @return Product line as a String
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     * Setter for the product line String.
     *
     * @param Desired produtctLine.
     */
    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    /**
     * Getter for the product scale String.
     *
     * @return Product scale as a String.
     */
    public String getProductScale() {
        return productScale;
    }

    /**
     * Setter for the prodcut scale String
     *
     * @param Desired productScale.
     */
    public void setProductScale(String productScale) {
        this.productScale = productScale;
    }

    /**
     * Getter for the product vendor String
     *
     * @return Product vendor as a String.
     */
    public String getProductVendor() {
        return productVendor;
    }

    /**
     * Setter for the product vendor String.
     *
     * @param Desired productVendor.
     */
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

    /**
     * Getter for product description String.
     *
     * @return Product description as a String.
     */
	public String getProductDescription() {
		return productDescription;
	}

    /**
     * Setter for the product description String.
     *
     * @param Desired productDescription.
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * Getter for the quantity left in stock of a procuct integer.
     *
     * @return Quantity in stock as an integer.
     */
    public int  getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * Setter for the quantity left in stock of a procuct integer
     *
     * @param Desired quantityInStock. 
     */
    public void setQuantityInStock(short quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * Getter for the buying price of a product as a float.
     *
     * @return Buying price as a float
     */
    public float getBuyPrice() {
        return buyPrice;
    }

    /**
     * Setter for the buying price of a product as a float.
     *
     * @param Desired buyPrice.
     */
    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    /**
     * Getter for the MSRP (Manufacturere's Suggested Retail Price) a float
     *
     * @return MSRP as a float.
     */
    public float getMSRP() {
        return MSRP;
    }

    /**
     * Setter for the MSRP (Manufacturere's Suggested Retail Price) a float
     *
     * @param Desired MSRP as a float.
     */
    public void setMSRP(float MSRP) {
        this.MSRP = MSRP;
    }
    

    @Override
    public String toString() {
        return productName;
    }

}

