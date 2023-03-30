<h1>RxJava Sample App</h1>
<p>
This sample app demonstrates the modern Android architecture pattern - MVVM(Model, View, ViewModel) using RxJava and Retrofit. The app provides detail information about movies fetched from the server.
</p>
<br>
<p align="center">
  <img src="https://user-images.githubusercontent.com/57670625/228626807-d472ff14-6c53-4f18-9ee3-1b91630ca200.png" width="23%"/>
  <img src="https://user-images.githubusercontent.com/57670625/228626803-f6a485eb-67c2-4ba6-bfdf-36040b45ad54.png" width="23%"/>
</p>
<br>

<!-- Tech Stack -->
<h2>Tech Stack</h2>
<ul>
<li>Minumum SDK Level: 21</li>
<li>100% Kotlin</li>
<li><a href="https://developer.android.com/topic/architecture">MVVM Pattern</a>: Industry-recognized software architecure pattern supported by Google</li>
<li>ViewModel: Exposes data streams as a state holder</li>
<li>Lifecycle: Observes Android lifecycles and handle operations to a change in the lifecycle status</li>
<li><a href="https://github.com/ReactiveX/RxJava">RxJava</a>: Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences</li>
<li><a href="https://github.com/ReactiveX/RxAndroid">RxAndroid</a>: Reactive extenstions for Android</li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a>: Type-safe REST client for Android, Java and Kotlin developed by Square.  </li>
<li><a href="https://square.github.io/okhttp/">OkHttp</a> : 3rd party library sending and receive HTTP-based network requests built on top of the Okio library</li>
<li><a href="https://github.com/google/gson">GSON</a>: Java library that can be used to convert Java Objects into their JSON representation</li>
</ul>
<br>

<!-- RxJava Description -->
<h2>RxJava</h2>
<p>RxJava is a Java VM implementation of ReactiveX a library for composing asynchronous and event-based programs by using observable sequences.</p>

<h3>Relationship between Observable and Observer</h3>
<p>"Observable" and "Observer" are two key components of RxJava. We need to understand how data streams work within RxJava.</p>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/228633987-f68a5965-4e21-4406-9b5c-c83c6616de38.jpg" width="35%"/>
</p>
<ul>
  <li><b>Oberservable</b>: Observable is a speaker which broadcasts the data value.</li>
  <li><b>Operator</b>: Operator converts or modifies the data emitted from Observable before Observer receives the data.</li>
  <li><b>Observer</b>: Observer is a subscriber of Observer. It receives the data emitted from Observable.</li>
  <li><b>Subscription</b>: A relationship between Observable and and a single Observer. There can be multiple Observers subscribed to a single Observable.</li>
</ul>
<br>

<h3>Schedulers</h3>
<p>Schedulers is responsible for managing which thread to execute tasks related to the operation of an Observable chain. There are two main Schedulers widely used in Android platfrom.</p>
<ul>
  <li><b>Schedulers.io()</b>: This is used to perform non-CPU-intensive operations like making network calls, reading disc/files, database operations, etc., This maintains a pool of threads</li>
  <li><b>AndroidSchedulers.mainThread()</b> This provides us access to the main thread of the application to perform actions like updating the UI. We shouldn’t perform any intensive operations on this thread as ANR dialog can be thrown.</li>
</ul>
<br>

<h3>How to subscribe to Observable</h3>
<pre><code>fun getAllMovies() {
    val response = repository.getAllMovies()
    response.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(observer)
}
</code></pre>
<ul>
  <li>When we call <b>getAllMovies()</b>, repository returns a response as "Observable" object from API call</li>
  <li><b>subscribeOn(Schedulers.io())</b>: Tells Observable to run the task(fetching data from the server) on a background thread.</li>
  <li><b>observeOn(AndroidSchedulers.mainThread())</b>: Tells the Observer to receive the data on the main UI thread so that you can update the screen.</li>
  <li><b>subscribe()</b>: Takes Observer as a parameter which receives the data emitted by Observable.</li>
</ul>
<br>

<h3>How to handle a subscription</h3>
<p>Observer needs to take a proper action(update the screen) after receiving data emitted from Observable. Observer object needs to implement following interfaces to handle the result of data received from Observable.</p>
<pre><code>private val observer: Observer<Movies> = object : Observer<Movies> {
        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(t: Movies) {
            _movieList.postValue(t)
        }

        override fun onError(e: Throwable) {
            _movieList.postValue(null)
        }

        override fun onComplete() {
        }

    }
</code></pre>

<ul>
  <li><b>onSubscribe()</b>: Called when Observer subscribes to Observable. It contains a Disposable instance as parameter whose Disposable.dispose() can be called anytime to cancel the connection or dispose the subscription when an Observer no longer wants to listen to Observable. In Android, disposable are very useful in avoiding memory leaks.</li>
  <li><b>onNext()</b>: Called when Observable starts emitting the data.</li>
  <li><b>onError()</b>: Called if any error occurs.</li>
  <li><b>onComplete()</b>: Called when Observable completes the emission of all the items.</li>
</ul>
<br>

<!-- App Architecture -->
<h2>App Architecture</h2>
<p>This sample was built with Google's recommended modern app architecture - MVVM pattern. By separating multiple app components into two layers
- UI and Data, the app is scalable, maintainable and testable.</p>
<ul>
  <li>Architectural Principles</li>
    <ul>
      <li>Separations of concerns</li>
      <li>Drive UI from data models</li>
      <li>Single source of truth</li>
      <li>Unidirectional Data Flow</li>
   </ul>
</ul>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/228396945-a6f69b5d-730a-4ce4-b1b3-bd4f24124070.jpg" width="85%"/>
</p>

<h3>UI Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/228909919-3e7b3645-dd22-479a-9c89-961c5e7f1ece.jpg" width="85%"/>
</p>
<p>UI layer displays the application data and serves as the primary point for user interactions. Whenever the app data changes, the UI should update to reflect changes made by either user interaction or external input.</p>
<ul>
  <li>The main activity has a fragment container view and two fragments are replaced following user interactions. Main fragment displays a list of movies fetched from the server. When the user selects one of the movies, detailed information is displayed in the detail fragment.</li>
  <li>MainViewModel holds state and plays as a bridge between UI elements and the data layer</li>
  <li>UI elements request actions to ViewModel and observer ViewModel's livedatas to automatically update screens</li>
</ul>
<br>

<h3>Data Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/228909915-f4e4c15e-dd92-4128-8f4e-a8a3003a3ac8.jpg" width="85%"/>
</p>
<p>Data layer is reponsible for containing application data and business logics. The data layer is consisted of repositories and data sources. It is important to keep each repository as a single source of truth.</p>
<ul>
  <li>Repository is a single source of truth and requests data to APIService which fetches data from the network server.</li>
  <li>APIService gets data from the server using Retrofit library.</li>
</ul>
<br>

<!-- References -->
<h2>References</h2>
<p>The purpose of this project was to understand how to use RxJava to operate asynchronous operations in Android apps. You can check out more information about RxJava in following links.</p>
<li><a href="https://www.geeksforgeeks.org/types-of-observables-in-rxjava/">Types of Observables in RxJava</a></li>
<li><a href="https://c1ctech.com/android-app-using-rxjava-rerofit-with-mvvm-architecture/">Android App using RxJava, Rerofit with MVVM Architecture</a></li>
 
