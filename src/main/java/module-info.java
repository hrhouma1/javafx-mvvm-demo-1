module com.demo.mvvm {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens com.demo.mvvm to javafx.fxml;
    opens com.demo.mvvm.view to javafx.fxml;
    
    exports com.demo.mvvm;
    exports com.demo.mvvm.model;
    exports com.demo.mvvm.viewmodel;
    exports com.demo.mvvm.view;
}

