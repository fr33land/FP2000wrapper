package lt.freeland.FP2000;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class FP2000Cmd 
	{
		private static byte seq = 0x21;
		private static byte fixedOffset = 0x20;
		private final char[] paidModeArr = new char[] { 'P', 'N', 'C', 'D', 'I', 'J', 'K', 'L' };
	
		public FP2000Cmd() 
			{
			}
		
		public ByteArrayOutputStream statusOfFiscalTransaction(Character opParam) 
			{
				try {
						ByteArrayOutputStream dataByteOutStream = new ByteArrayOutputStream();
						String dataStr = "";

						if (opParam != null && !opParam.toString().isEmpty()) 
							{
								dataStr = opParam.toString();
							}

						char[] charArr = dataStr.toCharArray();
						for (int i = 0; i < charArr.length; i++) 
							{
								dataByteOutStream.write(getCorrectByte(charArr[i]));
							}

						byte[] header = { 0x01, countByteLen(dataByteOutStream.toByteArray()), seq, 0x4c };
	
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
						outputStream.write(header);
						outputStream.write(dataByteOutStream.toByteArray());
						outputStream.write(0x05);
						outputStream.write(generateCheckSum(outputStream.toByteArray()));
						outputStream.write(0x03);

						byte[] cmdArrParse = outputStream.toByteArray();
						String cmdHex = "";
						for (int j = 0; j < cmdArrParse.length; j++) 
							{
								cmdHex = cmdHex + " " + Integer.toHexString(cmdArrParse[j]);
							}
						System.out.println("Sending command statusOfFiscalTransaction: " + cmdHex);
						seq++;
						return outputStream;
					} 
				catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				return null;
			}
		
		public ByteArrayOutputStream calculationOfTotal(String line1, String line2, Character paidMode, Character sign, BigDecimal amount) 
			{
				ByteArrayOutputStream dataByteOutStream = new ByteArrayOutputStream();
				try
					{
						if (line1 != null && !line1.isEmpty()) 
							{
								if (line1.length() > 30) 
									{
										line1 = line1.substring(0, 30);
									}
								char[] charArr = line1.toCharArray();
								for (int i = 0; i < charArr.length; i++) 
									{
										dataByteOutStream.write(getCorrectByte(charArr[i]));
									}					
							}
	
					if (line2 != null && !line2.isEmpty()) {
						dataByteOutStream.write(0x0A);
						if (line2.length() > 30) {
							line2 = line2.substring(0, 30);
						}
						char[] charArr = line2.toCharArray();
						for (int i = 0; i < charArr.length; i++) {
							dataByteOutStream.write(getCorrectByte(charArr[i]));
						}
					}
					dataByteOutStream.write(0x09);
	
					if (new String(paidModeArr).indexOf(paidMode) > -1) {
						dataByteOutStream.write(paidMode);
					}
	
					if (amount != null) {
						if (sign != null) {
							if (sign.compareTo(new Character('+')) == 0 || sign.compareTo(new Character('-')) == 0) {
								dataByteOutStream.write(sign);
							}
						}
						dataByteOutStream.write(amount.toString().getBytes());
					}
	
					byte[] data = dataByteOutStream.toByteArray();
					byte[] header = { 0x01, countByteLen(data), seq, 0x35 };
	
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
					outputStream.write(header);
					outputStream.write(data);
					outputStream.write(0x05);
					outputStream.write(generateCheckSum(outputStream.toByteArray()));
					outputStream.write(0x03);
	
					byte[] cmdArrParse = outputStream.toByteArray();
	
					String cmdHex = "";
	
					for (int j = 0; j < cmdArrParse.length; j++) {
						cmdHex = cmdHex + " " + Integer.toHexString(cmdArrParse[j]);
					}
	
					System.out.println("Sending command  calculationOfTotal: " + cmdHex);
					seq++;
					return outputStream;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
				return null;
			}
		
		public ByteArrayOutputStream closeFiscalReceipt() 
			{
				try 
					{
						String cmdData = "";
		
						byte[] data = cmdData.getBytes();
						byte[] header = { 0x01, countByteLen(data), seq, 0x38 };
		
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
						outputStream.write(header);
						outputStream.write(data);
						outputStream.write(0x05);
						outputStream.write(generateCheckSum(outputStream.toByteArray()));
						outputStream.write(0x03);
		
						byte[] cmdArrParse = outputStream.toByteArray();
		
						String cmdHex = "";
		
						for (int j = 0; j < cmdArrParse.length; j++) 
							{
								cmdHex = cmdHex + " " + Integer.toHexString(cmdArrParse[j]);
							}
	
						System.out.println("Sending command : " + cmdHex);
						seq = 0x20;
						return outputStream;
					} 
				catch (IOException e) 
					{
						e.printStackTrace();
					}
				
				return null;
			}
		
		public ByteArrayOutputStream cancelFiscalReceipt() 
			{
				try 
					{
						ByteArrayOutputStream dataByteOutStream = new ByteArrayOutputStream();
						dataByteOutStream.write("".getBytes());
		
						byte[] header = { 0x01, countByteLen(dataByteOutStream.toByteArray()), seq, 0x3c };
		
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
						outputStream.write(header);
						outputStream.write(0x05);
						outputStream.write(generateCheckSum(outputStream.toByteArray()));
						outputStream.write(0x03);
		
						byte[] cmdArrParse = outputStream.toByteArray();
						String cmdHex = "";
						
						for (int j = 0; j < cmdArrParse.length; j++) 
							{
								cmdHex = cmdHex + " " + Integer.toHexString(cmdArrParse[j]);
							}
						
						System.out.println("Sending command cancelFiscalReceipt: " + cmdHex);
						seq++;
						return outputStream;
					} 
				catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				return null;
			}
		
		public ByteArrayOutputStream closingNonFiscalReceipt(int close) 
			{
	
				try {
						ByteArrayOutputStream dataByteOutStream = new ByteArrayOutputStream();
						dataByteOutStream.write("".getBytes());
		
						byte[] header = { 0x01, countByteLen(dataByteOutStream.toByteArray()), seq, (byte) close };
		
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
						outputStream.write(header);
						outputStream.write(0x05);
						outputStream.write(generateCheckSum(outputStream.toByteArray()));
						outputStream.write(0x03);
		
						byte[] cmdArrParse = outputStream.toByteArray();
						String cmdHex = "";
						for (int j = 0; j < cmdArrParse.length; j++) {
							cmdHex = cmdHex + " " + Integer.toHexString(cmdArrParse[j]);
						}
						System.out.println("Sending command  closingNonFiscalReceipt: " + cmdHex);
						seq++;
						return outputStream;
					} 
				catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				return null;
			}
		
		private static byte countByteLen(byte[] data) 
			{
				int len = 0x04;
				len += data.length + fixedOffset;
				return (byte) len;
			}

		private static byte[] generateCheckSum(byte[] cmdPart) 
			{
				int crcSum = 0x00;
				ByteArrayOutputStream byteCheckSumBytes = new ByteArrayOutputStream();
	
				for (int i = 1; i < cmdPart.length; i++) 
					{
						if (cmdPart[i] < 0) 
							{
								String hexLastTwo = Integer.toHexString(cmdPart[i]);
								hexLastTwo = hexLastTwo.substring(hexLastTwo.length() - 2);
								crcSum += Integer.parseInt(hexLastTwo, 16);
							} 
						else 
							{
								crcSum += cmdPart[i];
							}	
					}
	
				String hexString = Integer.toHexString(crcSum);
				for (int i = hexString.length(); i < 4; i++) 
					{
						hexString = "0" + hexString;
					}
	
				for (int i = 0; i < hexString.length(); i++) 
					{
						String hex = "3" + hexString.charAt(i);
						int hx = Integer.parseInt(hex, 16);
						byteCheckSumBytes.write(hx);
					}
	
				return byteCheckSumBytes.toByteArray();
			}
		
		private static byte getCorrectByte(char chr) 
			{
				switch (chr) 
					{
						case 'Ą':
							return (byte) 0xc0;
						case 'ą':
							return (byte) 0xe0;
						case 'Č':
							return (byte) 0xc8;
						case 'č':
							return (byte) 0xe8;
						case 'Ę':
							return (byte) 0xc6;
						case 'ę':
							return (byte) 0xe6;
						case 'Ė':
							return (byte) 0xcb;
						case 'ė':
							return (byte) 0xeb;
						case 'Į':
							return (byte) 0xc1;
						case 'į':
							return (byte) 0xe1;
						case 'Š':
							return (byte) 0xd0;
						case 'š':
							return (byte) 0xf0;
						case 'Ų':
							return (byte) 0xd8;
						case 'ų':
							return (byte) 0xf8;
						case 'Ū':
							return (byte) 0xdb;
						case 'ū':
							return (byte) 0xfb;
						case 'Ž':
							return (byte) 0xde;
						case 'ž':
							return (byte) 0xfe;
						default:
							return (byte) chr;
					}
			}
}
