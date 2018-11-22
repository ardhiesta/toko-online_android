
package id.web.ardhi.tokoonline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("data")
    @Expose
    private List<Barang> data = null;

    public List<Barang> getData() {
        return data;
    }
}
