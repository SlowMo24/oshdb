package org.heigit.bigspatialdata.oshdb.examples.osmatrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.geotools.data.simple.SimpleFeatureSource;
import org.heigit.bigspatialdata.oshdb.examples.osmatrix.OSMatrixProcessor.TABLE;
import org.heigit.bigspatialdata.oshdb.osh.OSHEntity;
import org.heigit.bigspatialdata.oshdb.osm.OSMEntity;

public class LanduseAllotments extends Attribute{

	private static final Logger logger = Logger.getLogger(LanduseAllotments.class);
	
	Set<String> values = new HashSet<String>();
	
	@Override
	public String getName() {
		return "allotments_area";
	}

	@Override
	public String getDescription() {
		return "The area covered by allotments given in square meters.";
	}

	@Override
	public List<TABLE> getDependencies() {
		return Arrays.asList(TABLE.NODE);
	}
	@Override
	protected boolean needArea(TABLE table) {
		return true;
	}

	@Override
	public String getTitle() {
			return "Area of allotments";
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