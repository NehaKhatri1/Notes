package aws.rekognition.image.model;

public class BoundingBoxOfFace {
	
	
	Double width;
	Double height;
	Double left;
	Double top;
	String collectionId;
	
	
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getLeft() {
		return left;
	}
	public void setLeft(Double left) {
		this.left = left;
	}
	public Double getTop() {
		return top;
	}
	public void setTop(Double top) {
		this.top = top;
	}
	public String getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	
	
	
}
