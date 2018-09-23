
package business.customersubsystem;

import business.externalinterfaces.ICreditCard;

class CreditCard implements ICreditCard {
    String nameOnCard;
    String expirationDate;
    String cardNum;
    String cardType;
    CreditCard(String nameOnCard,String expirationDate,
               String cardNum, String cardType) {
        this.nameOnCard=nameOnCard;
        this.expirationDate=expirationDate;
        this.cardNum=cardNum;
        this.cardType=cardType;
    }

    /**
	 * @return  the nameOnCard
	 * @uml.property  name="nameOnCard"
	 */
    public String getNameOnCard() {
        return nameOnCard;
    }

    /**
	 * @return  the expirationDate
	 * @uml.property  name="expirationDate"
	 */
    public String getExpirationDate() {
        return expirationDate;
    }


    /**
	 * @return  the cardNum
	 * @uml.property  name="cardNum"
	 */
    public String getCardNum() {
        return cardNum;
    }

 
    /**
	 * @return  the cardType
	 * @uml.property  name="cardType"
	 */
    public String getCardType() {
        return cardType;
    }

}
