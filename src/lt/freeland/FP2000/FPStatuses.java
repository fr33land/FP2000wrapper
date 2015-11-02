package lt.freeland.FP2000;

public enum FPStatuses {
	
	
	S07 ("07","Reserved" ),
	S06 ("06","Electronic journal error" ),
	S05 ("05","General error or electronic journal error, code of incoming command is invalid, incoming data has syntax error" ),
	S04 ("04","Failure in printing mechanism" ),
	S03 ("03","Display is disconected" ),
	S02 ("02","The clock needs settings" ),
	S01 ("01","Code of incoming command is invalid" ),
	S00 ("00","Incoming data has syntax error" ),
	
	S17 ("17","Reserved" ),
	S16 ("16","Not used" ),
	S15 ("15","Printer cover is opened" ),
	S14 ("14","RAM failure after power ON" ),
	S13 ("13","RAM backup battery low" ),
	S12 ("12","Operational memory was cleared" ),
	S11 ("11","Command cannot be performed in the current fiscal mode" ),
	S10 ("10","If during command some of the fields for the sums overflow. Status 11 will also be set and the command will not cause changes to the data in the printer" ),
	
	S27 ("27","Reserved" ),
	S26 ("26","Exchange fiscal receipt open" ),
	S25 ("25","Non-fiscal receipt has been opened" ),
	S24 ("24","Journal near end" ),
	S23 ("23","A fiscal receipt has been opened (Both normal or exchange)" ),
	S22 ("22","Journal paper end" ),
	S21 ("21","Paper near end - both journal and receipt paper rolls." ),
	S20 ("20","No paper - valid for both paper rolls. If the flag is raised during a print-related command it will be rejected and the status of the printer will remain unchanged" ),
	
	
	S37 ("37","Reserved" ),
	S36 ("36","SW2 state. Selects serial speed." ),
	S35 ("35","SW3 state. Selects serial speed." ),
	S34 ("34","SW4 state. Enables the 'transparent display' mode." ),
	S33 ("33","SW5 state. Automatically cutting of the receipt." ),
	S32 ("32","SW6 state. Half or Full cut." ),
	S31 ("31","SW7 state. Not used." ),
	S30 ("30","SW8 state. Not used." ),
	S47 ("47","Reserved" ),
	S46 ("46","Fiscal memory number programmed" ),
	S45 ("45","OR of all mistakes 40, 44, 50." ),
	S44 ("44","Fiscal memory is full." ),
	S43 ("43","There is place for not more than 50 entries in the FM." ),
	S42 ("42","Serial number programmed" ),
	S41 ("41","VAT registration number programmed" ),
	S40 ("40","Error writing to fiscal memory" ),
	S57 ("57","Reserved" ),
	S56 ("56","Training mode" ),
	S55 ("55","Fiscal memory read error" ),
	S54 ("54","VAT rates have been entered at least once" ),
	S53 ("53","The printer is in the fiscal mode" ),
	S52 ("52","Last fiscal closure not OK" ),
	S51 ("51","The fiscal memory has been formatted" ),
	S50 ("50","The fiscal memory is in the 'read-only' mode" );
	
	private final String caption;
	private final String id;

	private FPStatuses(String id, String caption) {
		this.caption = caption;
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}


	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return this.getCaption();
	}

}
