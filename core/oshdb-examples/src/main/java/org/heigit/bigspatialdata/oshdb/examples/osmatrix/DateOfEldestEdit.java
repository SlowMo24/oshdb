package org.heigit.bigspatialdata.oshdb.examples.osmatrix;


import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.geotools.data.simple.SimpleFeatureSource;
import org.heigit.bigspatialdata.oshdb.examples.osmatrix.OSMatrixProcessor.TABLE;
import org.heigit.bigspatialdata.oshdb.osh.OSHEntity;
import org.heigit.bigspatialdata.oshdb.osm.OSMEntity;


public class DateOfEldestEdit extends Attribute {
	
	private static final Logger logger = Logger.getLogger(DateOfEldestEdit.class);

	@Override
	public double defaultValue() {
		return Double.MAX_VALUE;
	};

	@Override
	public String getName() {
		return "dateOfEldestEdit";
	}

	@Override
	public String getDescription() {
		return "The date of the eldest edit to any object within the given cell.";

	}

	@Override
	public List<TABLE> getDependencies() {
		return Arrays.asList(TABLE.NODE, TABLE.WAY, TABLE.RELATION);

	}

	@Override
	protected boolean needArea(TABLE table) {
		return false;
	}

	@Override
	public String getTitle() {
		return "Date of eldest edit";
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