<<<<<<< HEAD
## Marketplace
Android Application build in Java for buying and selling products of choice
### Code Beat
[![codebeat badge](https://codebeat.co/badges/c816344e-6c60-4cbd-a607-7ea2ad1ec326)](https://codebeat.co/projects/github-com-andrew-1000-marketplace-feature-marketplace)
## Getting Started
In order to get a copy of the project up and running on your local machine for development
and testing purposes, the steps below can be helpful
1. Access the repository in which the application was hosted
2. Folk the project and a copy will be saved in your account
3. Open the project from your repo and clone or download zip, this will get to you local machine
4. Open the application in Android Studio, if it is zipped, unzipp then it can be opened using android studio

### Prerequisites
Before installing the application, ensure the following is installed
```

1. Java is installed on your pc - can be downloaded from https://www.oracle.com/java/technologies/javase-jre8-downloads.html
2. Android Studio installed,preferably 3.6.1
```
### Installing
```
Start Android Studio
```
```
Click on File > New > Import Project
```
##
Import Dialogue shows up
Navigate to the root folder of your application a look for
```
settings.gradle
```
```
Click OK and project importation will begin
```
## License
    
   This project is licensed under the MIT Licence - see the [LICENSE.md] (LICENSE.md) file for details

## Author 
    * **Andrew Ambia** - *Initial Work* - [Marketplace]*
 

## Built With
* [Robolectric](http://robolectric.org/) - The test framework used to run Unit Tests on JVM
* [Maven](https://maven.apache.org/) - Dependency Management
* [Butterknife](https://jakewharton.github.io/butterknife/) - Used for binding views
```java
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtusername) EditText mUserName;
    @BindView(R.id.btnProceed) Button mProceed;
    @BindView(R.id.yourname) TextView getGetYourName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        
    }
}
 

    
  
=======
# Marketplace
Android Application build in Java for buying and selling products of choice
>>>>>>> 4fc3e47636ee6a7bd4760075e7054a631ce508da
