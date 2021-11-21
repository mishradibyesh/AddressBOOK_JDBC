package com.addressbook;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.addressbook.AddressBookDBService;
import com.addressbook.PersonInformation;

public class AddressBookDataCountTest {
	AddressBookDBService addressbookservice = new AddressBookDBService();
	@Test
	public void AddressBookDataRetrievedDFromDB_MatchCount() throws Exception {


		List<PersonInformation> list = addressbookservice.getDataFromDB();

		int actual_size = 3;
		Assert.assertEquals(actual_size , list.size());
	}

	@Test
	public  void UpdateExistingRecord() throws Exception {
		addressbookservice.updateExistingTable();

	}
}
