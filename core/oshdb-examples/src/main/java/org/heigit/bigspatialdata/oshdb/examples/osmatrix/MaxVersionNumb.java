package org.heigit.bigspatialdata.oshdb.examples.osmatrix;

import java.util.Arrays;
import java.util.List;
import org.geotools.data.simple.SimpleFeatureSource;
import org.heigit.bigspatialdata.oshdb.examples.osmatrix.OSMatrixProcessor.TABLE;
import org.heigit.bigspatialdata.oshdb.osh.OSHEntity;
import org.heigit.bigspatialdata.oshdb.osm.OSMEntity;

public class MaxVersionNumb extends Attribute{

	@Override
	public String getName() {
		return "maxVersionNumb";
	}

	@Override
	public String getDescription() {
		return "The maximum version number of all objects within the given cell.";
	}

	@Override
	public List<TABLE> getDependencies() {
		return Arrays.asList(TABLE.NODE,TABLE.WAY,TABLE.RELATION);
	}

	@Override
	public double defaultValue() {
		return Double.MIN_VALUE;
	}

	@Override
	protected boolean needArea(TABLE table) {
	    return false;
	}

	@Override
	public String getTitle() {
		return "Maximum version number";
	}

  @Override
  public AttributeCells compute(SimpleFeatureSource cellsIndex, OSHEntity<OSMEntity> osh, TagLookup tagLookup,
      List<Long> timestampsList, int attributeId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void aggregate(AttributeCells gridcellOutput, AttributeCells oshresult, List<Long> timestampList) {
    // TODO Auto-generated method stub
    
  }

}