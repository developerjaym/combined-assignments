package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	private HashMap<FatCat, Set<Capitalist>> mapOfRelationships = new HashMap<>();
	private HashSet<Capitalist> sackOfEverybody = new HashSet<>();
	
	private int counter = 0;
	
	
	/**
     * Adds a given element to the hierarchy.
     * <p> 1
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p> 2
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p> 3
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p> 4
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
	@Override
	public boolean add(Capitalist capitalist)
	{
		if(capitalist == null)
			return false;
		
		if(sackOfEverybody.contains(capitalist))
			return false;
		
		if(capitalist.hasParent() && !sackOfEverybody.contains(capitalist.getParent()))//has a parent, parent not in hrchy yet
		{
			//add parent below
			if(add(capitalist.getParent()))
			{
				if(mapOfRelationships.get(capitalist.getParent()) == null)
				{
					mapOfRelationships.put(capitalist.getParent(), new HashSet<>());
				}
				mapOfRelationships.get(capitalist.getParent()).add(capitalist);
			}
			
        	//add capitalist below
			sackOfEverybody.add(capitalist);
			return true;
		}
		
		
		
		if(capitalist.hasParent() && sackOfEverybody.contains(capitalist.getParent()))//has a parent, parent is in hrchy already
		{
			if(mapOfRelationships.get(capitalist.getParent()) == null)
			{
				mapOfRelationships.put(capitalist.getParent(), new HashSet<>());
			}
			mapOfRelationships.get(capitalist.getParent()).add(capitalist);
			
			sackOfEverybody.add(capitalist);
			return true;
		}
		if(capitalist instanceof WageSlave && !capitalist.hasParent())
		{//no parent, not a parent itself
			return false;//do not add
		}
		if(capitalist instanceof FatCat)
		{
			sackOfEverybody.add(capitalist);//do I need to put it in the hierarchy?
			//add to map??
			return true;
		}
		return false;
	}
	
    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	if(capitalist == null)
    		return false;
        if(this.getElements().contains(capitalist))
        	return true;
        return false;
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
        
        HashSet<Capitalist> allSet = new HashSet<>();
        for(Capitalist c : sackOfEverybody)
        {
        	allSet.add(c.defCopy());
        }
        return allSet;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapOfRelationships == null) ? 0 : mapOfRelationships.hashCode());
		result = prime * result + ((sackOfEverybody == null) ? 0 : sackOfEverybody.hashCode());
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
		MegaCorp other = (MegaCorp) obj;
		if (mapOfRelationships == null) {
			if (other.mapOfRelationships != null)
				return false;
		} else if (!mapOfRelationships.equals(other.mapOfRelationships))
			return false;
		if (sackOfEverybody == null) {
			if (other.sackOfEverybody != null)
				return false;
		} else if (!sackOfEverybody.equals(other.sackOfEverybody))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MegaCorp [mapOfRelationships=" + mapOfRelationships + /*", sackOfSlaves=" +/* sackOfSlaves +/* ", sackOfCats="
				+ sackOfCats +*/ ", sackOfEverybody=" + sackOfEverybody + "]";
	}

	/**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	
    	HashSet<FatCat> set = new HashSet<>();
    	if(sackOfEverybody.isEmpty())
    		return set;
    	for(Capitalist fc : sackOfEverybody)
    	{
    		if(fc instanceof FatCat)
    			set.add((FatCat)fc.defCopy());
    	}
    	return set;
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
        //plug this into my hashmap
    	//return a clone of the value
    	
    	Set<Capitalist> set = mapOfRelationships.get(fatCat);
    	Set<Capitalist> cloneSet = new HashSet<>();
    	if(set ==  null)
    	{
    		return cloneSet;//to get rid of nullpointerexceptions
    	}
    	if(set.isEmpty())
    	{
    		return cloneSet;
    	}
    	for(Capitalist fc : set)
        {
    		cloneSet.add(fc.defCopy());
        }
    	return cloneSet;
    }
    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
        //return a deep clone of map of relationships
    	//go through each parent
    		//make a set of its children (clones!)
    		//add the parent (clone!) and its children to the map
    	
    	Set<FatCat> set = getParents();
    	Map<FatCat, Set<Capitalist>> returnMap = new HashMap<>();
    	if(set ==  null || set.isEmpty())
    	{
    		return returnMap;//to get rid of nullpointerexceptions
    	}
    	for(FatCat fc : set)
        {
    		Set<Capitalist> cloneChildren = getChildren((FatCat)fc);
    		Set<Capitalist> childClones = new HashSet<>();
    		for(Capitalist cloneKid : cloneChildren)
            {
    			childClones.add(cloneKid.defCopy());
            }
    		FatCat parentClone = (FatCat) fc.defCopy();
    		returnMap.put(parentClone, childClones);
        }
    	return returnMap;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
    	
    	List<FatCat> parentList = new ArrayList<>();
    	Capitalist c = capitalist;
    	if(capitalist == null)
    	{
    		return parentList;
    	}
    	if(!c.hasParent())
    	{
    		return parentList;
    	}
    	if(!this.has(capitalist.getParent()))
    	{
    		return parentList;
    	}
    	while(c.hasParent())
    	{
    		FatCat fc = (FatCat) c.getParent();
    		parentList.add((FatCat)fc.defCopy());
    		c = c.getParent();//go to its parent
    	}
    	return parentList;
    }
    public static void main(String[] args)
    {
    	MegaCorp mc = new MegaCorp();
    	mc.add(new WageSlave("s", 1, new FatCat("f", 2)));
    	
    }
}
