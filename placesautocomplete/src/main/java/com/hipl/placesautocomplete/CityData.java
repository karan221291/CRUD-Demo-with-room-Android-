package com.hipl.placesautocomplete;

import java.util.ArrayList;

/**
 * Created by karanjm on 24-09-2018.
 */

public class CityData {
    public class PostOffice {
        private String Name;

        public String getName() {
            return this.Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        private String Description;

        public String getDescription() {
            return this.Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        private String BranchType;

        public String getBranchType() {
            return this.BranchType;
        }

        public void setBranchType(String BranchType) {
            this.BranchType = BranchType;
        }

        private String DeliveryStatus;

        public String getDeliveryStatus() {
            return this.DeliveryStatus;
        }

        public void setDeliveryStatus(String DeliveryStatus) {
            this.DeliveryStatus = DeliveryStatus;
        }

        private String Taluk;

        public String getTaluk() {
            return this.Taluk;
        }

        public void setTaluk(String Taluk) {
            this.Taluk = Taluk;
        }

        private String Circle;

        public String getCircle() {
            return this.Circle;
        }

        public void setCircle(String Circle) {
            this.Circle = Circle;
        }

        private String District;

        public String getDistrict() {
            return this.District;
        }

        public void setDistrict(String District) {
            this.District = District;
        }

        private String Division;

        public String getDivision() {
            return this.Division;
        }

        public void setDivision(String Division) {
            this.Division = Division;
        }

        private String Region;

        public String getRegion() {
            return this.Region;
        }

        public void setRegion(String Region) {
            this.Region = Region;
        }

        private String State;

        public String getState() {
            return this.State;
        }

        public void setState(String State) {
            this.State = State;
        }

        private String Country;

        public String getCountry() {
            return this.Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }
    }

    private String Message;

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    private String Status;

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    private ArrayList<PostOffice> PostOffice;

    public ArrayList<PostOffice> getPostOffice() {
        return this.PostOffice;
    }

    public void setPostOffice(ArrayList<PostOffice> PostOffice) {
        this.PostOffice = PostOffice;
    }
}
