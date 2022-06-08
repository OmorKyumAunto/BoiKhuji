package com.example.boikhuji.Model;

import java.io.Serializable;

public class MyCartModel implements Serializable {
   String productName,productPrice,totalQuantity,currentTime,currentDate;
   int totalPrice;
   String documentId,status="Confirmed";

   public MyCartModel() {
   }

   public MyCartModel(String status) {
      this.status = status;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public MyCartModel(String productName, String productPrice, String totalQuantity, String currentTime, String currentDate, int totalPrice) {
      this.productName = productName;
      this.productPrice = productPrice;
      this.totalQuantity = totalQuantity;
      this.currentTime = currentTime;
      this.currentDate = currentDate;
      this.totalPrice = totalPrice;
   }

   public String getDocumentId() {
      return documentId;
   }

   public void setDocumentId(String documentId) {
      this.documentId = documentId;
   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public String getProductPrice() {
      return productPrice;
   }

   public void setProductPrice(String productPrice) {
      this.productPrice = productPrice;
   }

   public String getTotalQuantity() {
      return totalQuantity;
   }

   public void setTotalQuantity(String totalQuantity) {
      this.totalQuantity = totalQuantity;
   }

   public String getCurrentTime() {
      return currentTime;
   }

   public void setCurrentTime(String currentTime) {
      this.currentTime = currentTime;
   }

   public String getCurrentDate() {
      return currentDate;
   }

   public void setCurrentDate(String currentDate) {
      this.currentDate = currentDate;
   }

   public int getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(int totalPrice) {
      this.totalPrice = totalPrice;
   }
}
