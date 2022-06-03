package acme.testing.any.artifact;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyArtifactListTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "any/artifact/list-tool.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String Name, final String Code, final String Description, final String RetailPrice, final String Link) {
		super.clickOnMenu("Artifact", "Tool");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, Name);
		super.checkColumnHasValue(recordIndex, 1, Code);
		super.checkColumnHasValue(recordIndex, 2, Description);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", Name);
		super.checkInputBoxHasValue("code", Code);
		super.checkInputBoxHasValue("retail-price", RetailPrice);
		super.checkInputBoxHasValue("description", Description);
		super.checkInputBoxHasValue("link", Link);
	}

	// Ancillary methods ------------------------------------------------------

}
