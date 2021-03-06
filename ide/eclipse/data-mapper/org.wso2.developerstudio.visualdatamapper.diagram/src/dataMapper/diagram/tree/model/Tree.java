package dataMapper.diagram.tree.model;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	private List<Attribute> attributes;
	private List<Element> elements;
	private List<Tree> trees;
	private String name;
	private Tree parent;
	private int count;
	
	public Tree(){
		this.count=1;
		this.parent=null;
	}
	
	public Tree(Tree parent){
		this.parent=parent;
		this.count=parent.getCount()+1;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public void setCount(int value){
		this.count=value;
	}
	
	public Tree getParent(){
		return this.parent;
	}
	
	public List<Attribute> getAttributes(){
		if (attributes == null) {
            attributes = new ArrayList<Attribute>();
        }
		return this.attributes;
	}
	
	public List<Element> getElements(){
		if(elements == null){
			elements=new ArrayList<Element>();
		}
		return this.elements;
	}
	
	public List<Tree> getTrees(){
		if(trees == null){
			trees = new ArrayList<Tree>();
		}
		return this.trees;
	}
	
	public String getName(){
		if(name == null){
			name = "";
		}
		return this.name;
	}
	
	public void setAttributes(List<Attribute> attributes) {
		this.attributes=attributes;
	}
	
	public void setElements(List<Element> elements) {
		this.elements=elements;
	}
	
	public void setTrees(List<Tree> trees) {
		this.trees=trees;
	}
	
	public void setName(String name) {
		this.name=name;
	}

}
