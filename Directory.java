/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExtendibleHashingScheme;

import java.util.*;
/**
*/
public class Directory{
	public int globalDepth;
	public int mod;
	private File dataFile;
	private ArrayList<Record> presentation;

	public String currentBucket, hashvalue;
	
	public Directory(int globalDepth, int localDepth, int blockingFactor){
		this.globalDepth = globalDepth;
		this.mod= 10;
		this.dataFile = new File(blockingFactor, localDepth);
		this.presentation = new ArrayList<Record>();
		

		this.makepresentation();
	
		
	}

	public void insertKey(int key){
		
		int h = key % mod;
		Record r = this.getDirectoryRecord(h);
		Bucket b = r.getBucket();
		this.hashvalue = Bits.trim(Bits.generateBitString(h), this.globalDepth);
		this.currentBucket = b.toString();
	
		if(b.canInsertKey()){
			
			b.insertKey(key);
		
		} else {
			
			if(b.getLocalDepth() >= globalDepth){
				++this.globalDepth;
				this.makepresentation();
			}
			Bucket newBucket = b.splitBucket(mod);
			
			if(b.getLocalHashValue().equals(Bits.trim(Bits.generateBitString(h), b.getLocalDepth()))){
				b.insertKey(key);
			} else {
				newBucket.insertKey(key);
			}
			
			this.dataFile.addBucket(newBucket);
			this.makepresentation();
		}
	}

	public boolean searchKey(int key){
		
		int h = key % mod;
		Record r = this.getDirectoryRecord(h);
		Bucket b = r.getBucket();
		this.hashvalue = Bits.trim(Bits.generateBitString(h), this.globalDepth);
		this.currentBucket = b.toString();
		
		if(b.isKeyPresent(key)){
			return true;
		}
		return false;
	}

	public void deleteKey(int key){
		int h = key % mod;
		Record r = this.getDirectoryRecord(h);
		Bucket b = r.getBucket();
		this.hashvalue = Bits.trim(Bits.generateBitString(h), this.globalDepth);
		this.currentBucket = b.toString();
		
		if(b.isKeyPresent(key)){
			System.out.println("Key " + key + " is present in " + b);
			b.deleteKey(key);
			System.out.println("After deleting:" + b);
			if(b.isEmpty()){
				System.out.println("After deleting element bucket became empty so we will merge");
				Bucket mergingBucket = this.dataFile.getmergingBucket(b);
				System.out.println(""+ mergingBucket + " is the merging bucket.");
				
				if((mergingBucket != null) && (b.canMergeWith(mergingBucket))){
					this.dataFile.mergeBuckets(b, mergingBucket);
					this.makepresentation();
				}
			}
		}
	}

	private Record getDirectoryRecord(int key){
		String bitString = Bits.trim(Bits.generateBitString(key), this.globalDepth);
		for(Record r : this.presentation){
			if(bitString.equals(r.getGlobalHashValue())){
				return r;
			}
		}
		return null;
	}

	private void makepresentation(){
		this.presentation.clear();
		for(int i=0; i<Math.pow(2, globalDepth); i++){
			String bitString = Bits.generateBitString(i);
			this.presentation.add(new Record(Bits.trim(bitString, globalDepth), dataFile.getBucket(bitString)));
		}
	}

	public String toString(){
		StringBuilder a = new StringBuilder("*********************************************\n");
		for(Record b : this.presentation){
			a.append(b.toString() + "  \n");
			
		}
		a.append("******************************************************");
		return a.toString();
	}

	
}