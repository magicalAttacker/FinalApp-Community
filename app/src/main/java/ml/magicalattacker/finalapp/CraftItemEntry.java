package ml.magicalattacker.finalapp;

import android.os.Parcel;
import android.os.Parcelable;

public class CraftItemEntry implements Parcelable {
    private int id;
    private String info;

    protected CraftItemEntry(Parcel in) {
        id = in.readInt();
        info = in.readString();
        price = in.readDouble();
    }

    public static final Creator<CraftItemEntry> CREATOR = new Creator<CraftItemEntry>() {
        @Override
        public CraftItemEntry createFromParcel(Parcel in) {
            return new CraftItemEntry(in);
        }

        @Override
        public CraftItemEntry[] newArray(int size) {
            return new CraftItemEntry[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public CraftItemEntry(int id, String info, double price) {
        this.id = id;
        this.info = info;
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(info);
        dest.writeDouble(price);
    }
}
