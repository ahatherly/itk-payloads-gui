package uk.nhs.interoperability.payloads.gui.model;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class PayloadObjectSerialiseExclusions implements ExclusionStrategy {

	  public PayloadObjectSerialiseExclusions() {
	  }

	  public boolean shouldSkipClass(Class<?> clazz) {
		  return false;
	  }

	  public boolean shouldSkipField(FieldAttributes f) {
	      if (f.getName().equals("parentObjectXPath")) return true;
	      if (f.getName().equals("parentObjectNames")) return true;
		  return false;
	  }
	}