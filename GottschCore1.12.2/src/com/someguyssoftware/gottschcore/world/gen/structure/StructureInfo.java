/**
 * 
 */
package com.someguyssoftware.gottschcore.world.gen.structure;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.someguyssoftware.gottschcore.positional.ICoords;

import net.minecraft.block.Block;

/**
 * @author Mark Gottschling on Jul 11, 2019
 *
 */
public class StructureInfo implements IStructureInfo {
	private ICoords coords;
	
	private ICoords size;
	
	/*
	 * a list of all selected coords
	 */
//	private List<ICoords> coords;
	
	// TODO can we make these more generic
	private List<ICoords> chests;
	
	private List<ICoords> spawners;
	
	/*
	 * map by blockState. this method assumes that a list of blockstates will be provided to scan for.
	 */
	private Multimap<Block, ICoords> map;
	
	/**
	 * 
	 */
	public StructureInfo() {
		map = ArrayListMultimap.create();
	}

	/**
	 * @return the map
	 */
	@Override
	public Multimap<Block, ICoords> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	@Override
	public void setMap(Multimap<Block, ICoords> map) {
		this.map = map;
	}

	/**
	 * @return the size
	 */
	@Override
	public ICoords getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	@Override
	public void setSize(ICoords size) {
		this.size = size;
	}

	/**
	 * @return the coords
	 */
	@Override
	public ICoords getCoords() {
		return coords;
	}

	/**
	 * @param coords the coords to set
	 */
	@Override
	public void setCoords(ICoords coords) {
		this.coords = coords;
	}

	@Override
	public String toString() {
		return "StructureInfo [coords=" + coords + ", size=" + size + ", chests=" + chests + ", spawners=" + spawners + "]";
	}

}