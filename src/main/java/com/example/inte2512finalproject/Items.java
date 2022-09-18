package com.example.inte2512finalproject;

public class Items {

	private String Id;
	
	private String title;
	
	private String type;
	
	private String subType;
	
	private String loanType;
	
	private Integer numberOfCopies;
	
	private Float rentalFee;
	
	private String rentalStatus;

	public Items() {
		
	}

	

	public Items(String id, String title, String type, String loanType, Integer numberOfCopies,
			Float rentalFee, String subType, String rentalStatus) {
		Id = id;
		this.title = title;
		this.type = type;
		this.subType = subType;
		this.loanType = loanType;
		this.numberOfCopies = numberOfCopies;
		this.rentalFee = rentalFee;
		this.rentalStatus = rentalStatus;
	}

	public Items(String id, String title, String type, String loanType, Integer numberOfCopies, Float rentalFee,
			String rentalStatus) {
		Id = id;
		this.title = title;
		this.type = type;
		this.loanType = loanType;
		this.numberOfCopies = numberOfCopies;
		this.rentalFee = rentalFee;
		this.rentalStatus = rentalStatus;
	}

	public void setValue(Items items) {
		this.Id = items.getId();
		this.title = items.getTitle();
		this.type = items.getType();
		this.subType = items.getSubType();
		this.loanType = items.getLoanType();
		this.numberOfCopies = items.getNumberOfCopies();
		this.rentalFee = items.getRentalFee();
		this.rentalStatus = items.getRentalStatus();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Integer getNumberOfCopies() {
		return numberOfCopies;
	}

	public void setNumberOfCopies(Integer numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}

	public Float getRentalFee() {
		return rentalFee;
	}

	public void setRentalFee(Float rentalFee) {
		this.rentalFee = rentalFee;
	}

	public String getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Items [Id=" + Id + ", title=" + title + ", type=" + type + ", loanType=" + loanType
				+ ", numberOfCopies=" + numberOfCopies + ", rentalFee=" + rentalFee + ", rentalStatus=" + rentalStatus
				+ "]";
	}
	
}
