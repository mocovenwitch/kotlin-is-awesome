//import io.reactivex.Single
//import io.reactivex.disposables.CompositeDisposable
//import io.reactivex.observers.DisposableSingleObserver
//import java.util.concurrent.TimeUnit
//
//
//abstract class UseCase<in PARAM, MODEL> constructor(private val appSchedulers: AppSchedulers) {
//
//    private var disposables = CompositeDisposable()
//
//    fun executeUseCase(singleObserver: DisposableSingleObserver<MODEL>, param: PARAM, delay: Long = 0): Single<MODEL> {
//        val observable = buildUseCaseObservable(param)
//            .subscribeOn(appSchedulers.io())
//            .delay(delay, TimeUnit.MILLISECONDS)
//            .observeOn(appSchedulers.mainThread())
//            .unsubscribeOn(appSchedulers.io())
//
//        disposables.add(observable.subscribeWith(singleObserver))
//        return observable
//    }
//
//    fun executeUseCaseSync(param: PARAM): MODEL {
//        return buildUseCaseObservable(param).blockingGet()
//    }
//
//    protected abstract fun buildUseCaseObservable(param: PARAM): Single<MODEL>
//
//    fun dispose() {
//        if (!disposables.isDisposed) {
//            disposables.dispose()
//        }
//    }
//}
//
//// call
//getCategory.executeUseCase(object :
//    DisposableSingleObserver<Resource<Home>>() {
//    override fun onError(e: Throwable) {
//    }
//
//    override fun onSuccess(resource: Resource<Home>) {
//        // do things
//    }
//
//}, path)
//
//// build get category - implementation of UseCase
//class GetCategory @Inject constructor(appSchedulers: AppSchedulers, private val repository:
//CategoryRepository) : UseCase<String, Resource<Home>>(appSchedulers) {
//
//    override fun buildUseCaseObservable(param: String): Single<Resource<Home>> {
//        return repository.getData(param).map { home -> process(home) }
//    }
//
//    private fun process(home: Resource<Home>): Resource<Home> {
//        home.data?.let {
//            if (it.id.equals("home")) {
//                it.title = null
//                it.embedded?.featuredCollection?.title = null
//            }
//        }
//
//        return home
//    }
//}