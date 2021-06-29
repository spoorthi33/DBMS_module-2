/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtendibleHashingScheme;

import java.util.*;
/**
*/
class Record{
	StringBuilder globalHashValue;
	Bucket bucket;
	
	protected Record(boolean bit, Bucket bucket){
		this.globalHashValue = new StringBuilder();
		if(bit){
			this.globalHashValue.insert(0, 1);
		} else {
			this.globalHashValue.insert(0, 0);
		}
		this.bucket = bucket;
	}

	protected Record(String bitString, Bucket bucket){
		this.globalHashValue = new StringBuilder(bitString);
		this.bucket = bucket;
	}

	protected Record(String bitString, boolean bit, Bucket bucket){
		this.globalHashValue = new StringBuilder(bitString);
		if(bit){
			this.globalHashValue.insert(0, 1);
		} else {
			this.globalHashValue.insert(0, 0);
		}
		this.bucket = bucket;
	}

	public boolean equals(Object O){
		Record dR = (Record)O;
		return this.globalHashValue.toString().equals(dR.getGlobalHashValue());
	}

	public String toString(){
		StringBuilder s = new StringBuilder();
		int len = 10 - this.globalHashValue.length();
		s.append("|  ");
		for(int i=0; i<len; i++){
			s.append(" ");
		}
		s.append(this.globalHashValue.toString());
		s.append("  |  ");
		s.append(this.bucket.toString());
		// len = 11 - this.bucket.bucketName.length();
		// for(int i=0; i<len; i++){
		// 	s.append(" ");
		// }
		// s.append(this.bucket.bucketName);
		// s.append("  |");
		return s.toString(); // 3 + 10 + 5 + 11 + 3 = 6 + 15 + 11 = 6 + 26 = 32
	}

	protected String getGlobalHashValue(){
		return this.globalHashValue.toString();
	}
	protected Bucket getBucket(){
		return this.bucket;
	}
}