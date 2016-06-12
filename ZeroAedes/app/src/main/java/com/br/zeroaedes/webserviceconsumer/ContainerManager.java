package com.br.zeroaedes.webserviceconsumer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
//import android.hardware.camera2.params.Face;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
/*
import com.facebook.Session;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;*/
import com.br.zeroaedes.R;
import com.facebook.GraphResponse;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.pnikosis.materialishprogress.ProgressWheel;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import br.com.prolins.niceCook.R;
import br.com.prolins.niceCook.wsconsumer.event.AddAmigoEvent;
import br.com.prolins.niceCook.wsconsumer.event.AddAmigoFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.AdicionarReceitaDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.AdicionarReceitaDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.AlterarSenhaEvent;
import br.com.prolins.niceCook.wsconsumer.event.AlterarSenhaFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.CadastroEvent;
import br.com.prolins.niceCook.wsconsumer.event.CadastroFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.CadastroReceitaDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.CadastroReceitaDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.ComentarioDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.ComentarioDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.ComentariosListDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.ComentariosListDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.CurtidasListDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.CurtidasListDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.CurtirDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.CurtirDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.EditarPerfilEvent;
import br.com.prolins.niceCook.wsconsumer.event.EditarPerfilFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.EditarReceitaEvent;
import br.com.prolins.niceCook.wsconsumer.event.EditarReceitaFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.ExcluirPerfilvent;
import br.com.prolins.niceCook.wsconsumer.event.ExcluirPerfilFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.HideDialogEvent;
import br.com.prolins.niceCook.wsconsumer.event.ListarReceitasDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.ListarReceitasDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.LoginEvent;
import br.com.prolins.niceCook.wsconsumer.event.LoginFacebookEvent;
import br.com.prolins.niceCook.wsconsumer.event.LoginFacebookFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.LoginFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.LogoutEvent;
import br.com.prolins.niceCook.wsconsumer.event.LogoutFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.NotificationEvent;
import br.com.prolins.niceCook.wsconsumer.event.NotificationFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.ObterPerfilDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.ObterPerfilDownloadFailedEvent;

import br.com.prolins.niceCook.wsconsumer.event.PesquisaReceitaEvent;
import br.com.prolins.niceCook.wsconsumer.event.PesquisaReceitaFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.PesquisaUsuarioEvent;
import br.com.prolins.niceCook.wsconsumer.event.PesquisaUsuarioFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.PostarImageEvent1;
import br.com.prolins.niceCook.wsconsumer.event.PostarImageEvent2;
import br.com.prolins.niceCook.wsconsumer.event.PostarImageEvent3;
import br.com.prolins.niceCook.wsconsumer.event.PostarImageEventPerfil;
import br.com.prolins.niceCook.wsconsumer.event.PostarImageEventPrincipal;
import br.com.prolins.niceCook.wsconsumer.event.PostarImageFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.ReceitaDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.ReceitaDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.RecipesAddListDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.RecipesAddListDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.RecuperarSenhaEvent;
import br.com.prolins.niceCook.wsconsumer.event.RecuperarSenhaFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.RemoveAmigoEvent;
import br.com.prolins.niceCook.wsconsumer.event.RemoveAmigoFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.RemoverReceitaEvent;
import br.com.prolins.niceCook.wsconsumer.event.RemoverReceitaFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.ShowDialogEvent;
import br.com.prolins.niceCook.wsconsumer.event.VerificaLoginDownloadEvent;
import br.com.prolins.niceCook.wsconsumer.event.VerificaLoginDownloadFailedEvent;
import br.com.prolins.niceCook.wsconsumer.event.VerificaProfileEvent;
import br.com.prolins.niceCook.wsconsumer.event.VerificaProfileFailedEvent;
import br.com.prolins.niceCook.wsconsumer.response.DataResponseCadastro;
import br.com.prolins.niceCook.wsconsumer.response.DataResponseLogin;
import br.com.prolins.niceCook.wsconsumer.response.DataResponseLogout;
import br.com.prolins.niceCook.wsconsumer.response.ResponseAddAmigo;
import br.com.prolins.niceCook.wsconsumer.response.ResponseAddRecipeList;
import br.com.prolins.niceCook.wsconsumer.response.ResponseAdicionarReceita;
import br.com.prolins.niceCook.wsconsumer.response.ResponseAlterarSenha;
import br.com.prolins.niceCook.wsconsumer.response.ResponseCadastroReceita;
import br.com.prolins.niceCook.wsconsumer.response.ResponseComentario;
import br.com.prolins.niceCook.wsconsumer.response.ResponseComentariosList;
import br.com.prolins.niceCook.wsconsumer.response.ResponseCurtidas;
import br.com.prolins.niceCook.wsconsumer.response.ResponseCurtir;
import br.com.prolins.niceCook.wsconsumer.response.ResponseEditarPerfil;
import br.com.prolins.niceCook.wsconsumer.response.ResponseEditarReceita;
import br.com.prolins.niceCook.wsconsumer.response.ResponseExcluirPerfil;
import br.com.prolins.niceCook.wsconsumer.response.ResponseImage1;
import br.com.prolins.niceCook.wsconsumer.response.ResponseImage2;
import br.com.prolins.niceCook.wsconsumer.response.ResponseImage3;
import br.com.prolins.niceCook.wsconsumer.response.ResponseImagePerfil;
import br.com.prolins.niceCook.wsconsumer.response.ResponseImagePrincipal;
import br.com.prolins.niceCook.wsconsumer.response.ResponseNotifications;
import br.com.prolins.niceCook.wsconsumer.response.ResponsePesquisa;
import br.com.prolins.niceCook.wsconsumer.response.ResponsePesquisaReceita;
import br.com.prolins.niceCook.wsconsumer.response.ResponseProfile;
import br.com.prolins.niceCook.wsconsumer.response.ResponseProfileVerify;
import br.com.prolins.niceCook.wsconsumer.response.ResponseReceita;
import br.com.prolins.niceCook.wsconsumer.response.ResponseReceitaRemove;
import br.com.prolins.niceCook.wsconsumer.response.ResponseReceitasList;
import br.com.prolins.niceCook.wsconsumer.response.ResponseRecuperarSenha;
import br.com.prolins.niceCook.wsconsumer.response.ResponseVerificaLogin;
import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by Tiago Alencar on 29/12/15.
 */
public class ContainerManager {
    private static final String TAG = "ContainerManager";
    private static final String TAG_Debug = "Debug";

    private static WSClient mService;
    private static Context mContext;
    /*private static EventosManager mEventosManager;
    private static AtividadeManager mAtividadeManager;
    private static ComentarioManager mComentarioManager;
    private static SeguidoresManager mSeguidorManager;
    private static SeguindoManager mSeguindoManager;
    private static CurtidaManager mCurtidaManager;
    private static Perfil mPerfil;*/
    private static HashMap<String, Object> user;
    /*private static List<Seguidor> meusSeguidores;
    private static List<Seguindo> meusSeguindo;*/
    private static ImageLoader imageLoader;
    private static DisplayImageOptions options;

    Typeface typeface;

    public ContainerManager(Context context) {
        /*mEventosManager = eventosManager;
        this.mAtividadeManager = mAtividadeManager;
        this.mComentarioManager = mComentarioManager;
        this.mSeguidorManager = mSeguidorManager;
        this.mSeguindoManager = mSeguindoManager;
        this.mCurtidaManager = mCurtidaManager;*/
        if (mService == null)
            mService = new WSClient(context);

        mContext = context;
        loadImagerLoader();
    }

    private void loadImagerLoader() {
        File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, "cache_folder");
        imageLoader = ImageLoader.getInstance();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .build();

        imageLoader.init(config);

        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.abc_tab_indicator_material)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
    }

    public void pessoaCadastro(String username,
                               String password,
                               String nome,
                               String Email,
                               String profileType,
                               String genre,
                               String Photo,
                               String WebProfileLink,
                               String FacebookToken) {
        EventBus.getDefault().post(new ShowDialogEvent());
        mService.getApiService().pessoaCadastro(username, password, nome, Email, profileType, genre, Photo, WebProfileLink, FacebookToken,
                new Callback<DataResponseCadastro>() {

                    @Override
                    public void success(DataResponseCadastro dataResponseCadastro, Response response) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        CadastroEvent login = new CadastroEvent(dataResponseCadastro);
                        login.setLoginResponse(dataResponseCadastro);
                        setLogin(login);
                        EventBus.getDefault().post(login);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new CadastroFailedEvent(error));
                    }
                });
    }

    //métodos para login

    public void login(String email, String senha) {
        EventBus.getDefault().post(new ShowDialogEvent());
        mService.getApiService().acessoEntrar(email, senha,
                new Callback<DataResponseLogin>() {
                    @Override
                    public void success(DataResponseLogin dataResponseLogin, Response response) {
                        EventBus.getDefault().post(new HideDialogEvent());

                        LoginEvent login = new LoginEvent(dataResponseLogin);
                        login.setLoginResponse(dataResponseLogin);
                        setLogin(dataResponseLogin);

                        EventBus.getDefault().post(login);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new LoginFailedEvent(error));

                    }
                });
    }

    public void login(String tokenExterno) {
        EventBus.getDefault().post(new ShowDialogEvent());
        mService.getApiService().acessoEntrar(tokenExterno,
                new Callback<DataResponseLogin>() {

                    @Override
                    public void success(DataResponseLogin dataResponseLogin, Response response) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        LoginFacebookEvent login = new LoginFacebookEvent(dataResponseLogin);
                        login.setLoginResponse(dataResponseLogin);
                        setLogin(dataResponseLogin);
                        EventBus.getDefault().post(login);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new LoginFacebookFailedEvent(error));
                    }
                });
    }

    public boolean verificaSessao() {

        SharedPreferences pref = mContext.getSharedPreferences(WSClient.PREFERENCIAS, Context.MODE_PRIVATE);
        String login = pref.getString(WSClient.LOGIN_TOKEN, "");

        isLogged(login);


        try {

            return !login.isEmpty();

        } catch (Exception e) {
            return false;
        }
    }

/*
    public void isLogged(String Token) {
        mService.getApiService().verificarAcesso(Token, new Callback<ResponseVerificaLogin>() {
            @Override
            public void success(ResponseVerificaLogin responseVerificaLogin, Response response) {
                EventBus.getDefault().post(new VerificaLoginDownloadEvent(responseVerificaLogin));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new VerificaLoginDownloadFailedEvent(error));
            }
        });
    }
*/

/*
    public void recuperarSenha(String email) {
        EventBus.getDefault().post(new ShowDialogEvent());
        mService.getApiService().recuperarSenha(email, new Callback<ResponseRecuperarSenha>() {
            @Override
            public void success(ResponseRecuperarSenha responseRecuperarSenha, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RecuperarSenhaEvent(responseRecuperarSenha));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RecuperarSenhaFailedEvent(error));

            }
        });
    }
*/

    //métodos para logout
/*
    public void logout() {
        mService.getApiService().accessLogout(new Callback<DataResponseLogout>() {
            @Override
            public void success(DataResponseLogout dataResponseLogout, Response response) {
                LogoutEvent logoutEvent = new LogoutEvent(dataResponseLogout);
                setLogout();
                EventBus.getDefault().post(logoutEvent);
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new LogoutFailedEvent(error));
            }
        });
    }
*/
    public void setLogout() {
        SharedPreferences pref = mContext.getSharedPreferences(WSClient.PREFERENCIAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(WSClient.LOGIN_TOKEN, "");
        editor.putString(WSClient.LOGIN_TOKEN_FACE, "");
        editor.putLong(WSClient.LOGIN_IDUSUARIO, 0);
        editor.commit();

    }

    public void setLoginFacebook(String tokenFacebook) {
        SharedPreferences pref = mContext.getSharedPreferences(WSClient.PREFERENCIAS, Context.MODE_PRIVATE);

        try {
            if (!tokenFacebook.isEmpty()) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(WSClient.LOGIN_TOKEN_FACE, tokenFacebook);
                editor.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    public void setLogin(DataResponseLogin login) {
        EventBus.getDefault().post(new ShowDialogEvent());
        if (login.Token == null) return;
        try {
            if (login.Token.isEmpty()) {
                EventBus.getDefault().post(new HideDialogEvent());
                Toast.makeText(mContext, login.Token, Toast.LENGTH_SHORT).show();
            }

            if (!login.Token.isEmpty()) {
                EventBus.getDefault().post(new HideDialogEvent());

                SharedPreferences pref = mContext.getSharedPreferences(WSClient.PREFERENCIAS, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = pref.edit();
                editor.putString(WSClient.LOGIN_TOKEN, login.Token);
                editor.putLong(WSClient.LOGIN_IDUSUARIO, login.ProfileId);
                editor.commit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLogin(CadastroEvent event) {
        if (event == null) return;
        DataResponseCadastro login = event.getLoginResponse();
        try {


            if (login.Token.isEmpty()) {
                Toast.makeText(mContext, "Erro no login...", Toast.LENGTH_SHORT);
            }

            if (!login.Token.isEmpty()) {
                SharedPreferences pref = mContext.getSharedPreferences(WSClient.PREFERENCIAS, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = pref.edit();
                editor.putString(WSClient.LOGIN_TOKEN, login.Token);
                editor.putLong(WSClient.LOGIN_IDUSUARIO, login.ProfileId);
                editor.commit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    public void alterarSenha(String senhaAtual, String novaSenha) {
        mService.getApiService().alterarSenha(senhaAtual, novaSenha, new Callback<ResponseAlterarSenha>() {
            @Override
            public void success(ResponseAlterarSenha responseAlterarSenha, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new AlterarSenhaEvent(responseAlterarSenha));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new AlterarSenhaFailedEvent(error));
            }
        });
    }

    public void excluirPerfil() {
        mService.getApiService().excluirPerfil(new Callback<ResponseExcluirPerfil>() {
            @Override
            public void success(ResponseExcluirPerfil responseExcluirPerfil, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ExcluirPerfilvent(responseExcluirPerfil));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ExcluirPerfilFailedEvent(error));
            }
        });
    }

    private DisplayImageOptions getOptions() {
        return options;
    }

    private ImageLoader getImageLoader() {
        return imageLoader;
    }

    public boolean exists(String url) {
        HttpURLConnection huc = null;
        URL endereco = null;
        try {
            endereco = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        int code = 0;
        try {
            huc = (HttpURLConnection) endereco.openConnection();
            huc.setRequestMethod("GET");  //OR  huc.setRequestMethod ("HEAD");
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            huc.connect();


            code = huc.getResponseCode();
            System.out.println(code);

        } catch (IOException e) {
            e.printStackTrace();
        }


        if (code == 404)
            return false;
        else
            return true;
    }

    public void loaderImage(String url, final ImageView imageView, final ProgressWheel wheel, final int placeholder) {

        try {
            imageView.setImageResource(placeholder);

            if (url == null || url.isEmpty() || imageView == null) {
                return;
            }

            Uri uri;


            if (url.contains("graph.facebook.com")) {
                uri = Uri.parse(url);
            } else {
                /*if (exists(WSClient.BASE_URL_IMAGES + url)) {*/
                uri = Uri.parse(WSClient.BASE_URL_IMAGES + url);
                /*} else {
                    uri = Uri.parse(WSClient.BASE_URL_IMAGES_NOVA + url);
                }*/
            }

            wheel.setBarColor(Color.RED);


            getImageLoader().loadImage(uri.toString(), options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                    try {
                        wheel.spin();
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    try {

                        wheel.stopSpinning();
                        imageView.setImageResource(placeholder);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    try {

                        wheel.stopSpinning();
                        imageView.setImageBitmap(loadedImage);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    try {

                        wheel.stopSpinning();
                        imageView.setImageResource(placeholder);
                    } catch (Exception e) {
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loaderImage(String url, final ImageView imageView) {

        try {

            imageView.setImageResource(R.drawable.tunedtransparente);

            if (url == null || url.isEmpty() || imageView == null) {
                return;
            }

            Uri uri;
            if (url.contains("graph.facebook.com")) {
                uri = Uri.parse(url);
            } else {
                /*if (exists(WSClient.BASE_URL_IMAGES + url)) {*/
                uri = Uri.parse(WSClient.BASE_URL_IMAGES + url);
                /*} else {
                    uri = Uri.parse(WSClient.BASE_URL_IMAGES_NOVA + url);

                }*/
            }


            final ProgressWheel wheel = new ProgressWheel(mContext);
            wheel.setBarColor(Color.RED);

            final ViewGroup rootFrameLayout = (ViewGroup) imageView.getParent();
            wheel.setLayoutParams(imageView.getLayoutParams());


            getImageLoader().loadImage(uri.toString(), options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    try {

                        rootFrameLayout.addView(wheel);
                        wheel.spin();
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    try {

                        wheel.stopSpinning();
                        rootFrameLayout.removeView(wheel);
                        imageView.setImageResource(R.drawable.tunedtransparente);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    try {
                        wheel.stopSpinning();
                        rootFrameLayout.removeView(wheel);
                        imageView.setImageBitmap(loadedImage);
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    try {

                        wheel.stopSpinning();
                        rootFrameLayout.removeView(wheel);
                        imageView.setImageResource(R.drawable.tunedtransparente);
                    } catch (Exception e) {
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Typeface getTypeFace() {
        if (typeface == null)
            typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/helvetica.ttf");

        return typeface;
    }

    public void uploadImagePerfil(String urlImageLocal) {
        EventBus.getDefault().post(new ShowDialogEvent());

        File photo = new File(urlImageLocal);
        TypedFile typedImage = new TypedFile("application/octet-stream", photo);

        mService.getApiService().uploadImagePerfil(typedImage, new Callback<ResponseImagePerfil>() {
            @Override
            public void success(ResponseImagePerfil responseImage, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageEventPerfil(responseImage));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageFailedEvent(error));
            }
        });

    }

    public void uploadImageRecipePrincipal(String urlImageLocal) {
        EventBus.getDefault().post(new ShowDialogEvent());
        File photo = new File(urlImageLocal);
        TypedFile typedImage = new TypedFile("application/octet-stream", photo);
        Log.i(TAG, "Dentro do Container >>> " + typedImage);
        mService.getApiService().uploadImageRecipePrincipal(typedImage, new Callback<ResponseImagePrincipal>() {
            @Override
            public void success(ResponseImagePrincipal responseImage, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageEventPrincipal(responseImage));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageFailedEvent(error));
            }
        });

    }

    public void uploadImageRecipe1(String urlImageLocal) {
        EventBus.getDefault().post(new ShowDialogEvent());
        File photo = new File(urlImageLocal);
        TypedFile typedImage = new TypedFile("application/octet-stream", photo);
        Log.i(TAG, "Dentro do Container >>> " + typedImage);
        mService.getApiService().uploadImageRecipe1(typedImage, new Callback<ResponseImage1>() {
            @Override
            public void success(ResponseImage1 responseImage, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageEvent1(responseImage));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageFailedEvent(error));
            }
        });

    }

    public void uploadImageRecipe2(String urlImageLocal) {
        EventBus.getDefault().post(new ShowDialogEvent());
        File photo = new File(urlImageLocal);
        TypedFile typedImage = new TypedFile("application/octet-stream", photo);
        Log.i(TAG, "Dentro do Container >>> " + typedImage);
        mService.getApiService().uploadImageRecipe2(typedImage, new Callback<ResponseImage2>() {
            @Override
            public void success(ResponseImage2 responseImage, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageEvent2(responseImage));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageFailedEvent(error));
            }
        });

    }

    public void uploadImageRecipe3(String urlImageLocal) {
        EventBus.getDefault().post(new ShowDialogEvent());
        File photo = new File(urlImageLocal);
        TypedFile typedImage = new TypedFile("application/octet-stream", photo);
        Log.i(TAG, "Dentro do Container >>> " + typedImage);
        mService.getApiService().uploadImageRecipe3(typedImage, new Callback<ResponseImage3>() {
            @Override
            public void success(ResponseImage3 responseImage, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageEvent3(responseImage));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PostarImageFailedEvent(error));
            }
        });

    }

    public HashMap<String, Object> getUser() {

        SharedPreferences pref = mContext.getSharedPreferences(WSClient.PREFERENCIAS, Context.MODE_PRIVATE);
        String jsonString = pref.getString(WSClient.USER_FACEBOOK, "");
        String jsonResponseString = pref.getString(WSClient.RESPONSE_FACEBOOK, "");

        HashMap<String, Object> userHash = new HashMap<String, Object>();
        userHash.put("user", jsonString);
        userHash.put("response", jsonResponseString);

        return userHash;

    }

    public void setUser(JSONObject user, GraphResponse response) {

        SharedPreferences pref = mContext.getSharedPreferences(WSClient.PREFERENCIAS, Context.MODE_PRIVATE);

        if (!user.toString().isEmpty()) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString(WSClient.USER_FACEBOOK, user.toString());
            editor.putString(WSClient.RESPONSE_FACEBOOK, response.toString());

            editor.commit();
        }

    }

    public void getListarNotifications(String offset) {
        EventBus.getDefault().post(new ShowDialogEvent());

        String get = "?offset=";

        mService.getApiService().getListarNotifications(get, offset, new Callback<ResponseNotifications>() {
            @Override
            public void success(ResponseNotifications responseNotifications, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new NotificationEvent(responseNotifications));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new NotificationFailedEvent(error));
            }
        });
    }


    public void cadastroReceita(String CookLevelId,
                                String RecipeType,
                                String Photo,
                                String Title,
                                String Description,
                                String Ingredients,
                                String PreparationMode,
                                String VisualizationMode,
                                String media,
                                String PreparationTime) {

        EventBus.getDefault().post(new ShowDialogEvent());
        mService.getApiService().cadastroReceita(CookLevelId,
                RecipeType,
                Photo,
                Title,
                Description,
                Ingredients,
                PreparationMode,
                VisualizationMode,
                media,
                PreparationTime, new Callback<ResponseCadastroReceita>() {
                    @Override
                    public void success(ResponseCadastroReceita responseCadastroReceita, Response response) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new CadastroReceitaDownloadEvent(responseCadastroReceita));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new CadastroReceitaDownloadFailedEvent(error));

                    }
                });
    }

    public void listarReceitas(String offset) {

        //EventBus.getDefault().post(new ShowDialogEvent());

        String get = "Get?offset=";

        mService.getApiService().getListarReceitas(get, offset, new Callback<ResponseReceitasList>() {
            @Override
            public void success(ResponseReceitasList responseReceitasList, Response response) {

                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ListarReceitasDownloadEvent(responseReceitasList));

            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ListarReceitasDownloadFailedEvent(error));
            }
        });
    }

    public void getListarReceitasByProfile(int offset, String id) {
        String get = "GetByProfile?offset=";
        String profile = "&profileId=";
        mService.getApiService().getListarReceitasByProfile(get, String.valueOf(offset), profile, id, new Callback<ResponseReceitasList>() {
            @Override
            public void success(ResponseReceitasList responseReceitasList, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ListarReceitasDownloadEvent(responseReceitasList));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ListarReceitasDownloadFailedEvent(error));
            }
        });

    }

    public void getReceita(String id) {


        String get = "GetRecipe?id=";

        mService.getApiService().getReceita(get, id, new Callback<ResponseReceita>() {
            @Override
            public void success(ResponseReceita responseReceita, Response response) {


                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ReceitaDownloadEvent(responseReceita));

            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ReceitaDownloadFailedEvent(error));
            }
        });
    }

    public void verificaPerfil(String ProfileId) {
        mService.getApiService().verificaProfile(ProfileId, new Callback<ResponseProfileVerify>() {
            @Override
            public void success(ResponseProfileVerify responseProfileVerify, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new VerificaProfileEvent(responseProfileVerify));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new VerificaProfileFailedEvent(error));

            }
        });
    }

    public void obterProfile(String ProfileId) {
        mService.getApiService().obterPerfil(ProfileId, new Callback<ResponseProfile>() {
            @Override
            public void success(ResponseProfile responseProfile, Response response) {
                Log.i(TAG, "SUCESSO!");
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ObterPerfilDownloadEvent(responseProfile));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ObterPerfilDownloadFailedEvent(error));

            }
        });
    }

    public void editarPerfil(String ProfileId,
                             String Name,
                             String ProfileImage,
                             String Bio,
                             String Nasc,
                             String Email,
                             String WebLinkProfile,
                             String PersonalLink,
                             String ProfileTypeId,
                             String Genre) {
        EventBus.getDefault().post(new ShowDialogEvent());

        mService.getApiService().editarPerfil(ProfileId, Name, ProfileImage, Bio, Nasc, Email,
                WebLinkProfile, PersonalLink, ProfileTypeId, Genre, new Callback<ResponseEditarPerfil>() {
                    @Override
                    public void success(ResponseEditarPerfil responseEditarPerfil, Response response) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new EditarPerfilEvent(responseEditarPerfil));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new EditarPerfilFailedEvent(error));

                    }
                });
    }

    public void curtirReceita(String recipeId) {
        mService.getApiService().curtirReceita(recipeId, new Callback<ResponseCurtir>() {
            @Override
            public void success(ResponseCurtir responseCurtir, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new CurtirDownloadEvent(responseCurtir));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new CurtirDownloadFailedEvent(error));

            }
        });
    }

    public void obterCurtidas(String recipeId) {
        mService.getApiService().obterCurtidas(recipeId, new Callback<ResponseCurtidas>() {
            @Override
            public void success(ResponseCurtidas responseCurtidas, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new CurtidasListDownloadEvent(responseCurtidas));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new CurtidasListDownloadFailedEvent(error));
            }
        });
    }

    public void getComentarios(String RecipeId) {
        mService.getApiService().getComentarios(RecipeId, new Callback<ResponseComentariosList>() {
            @Override
            public void success(ResponseComentariosList responseComentarios, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ComentariosListDownloadEvent(responseComentarios));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ComentariosListDownloadFailedEvent(error));

            }
        });
    }

    public void comentarReceita(String RecipeId, String Comentario) {
        mService.getApiService().comentar(RecipeId, Comentario, new Callback<ResponseComentario>() {
            @Override
            public void success(ResponseComentario responseComentario, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ComentarioDownloadEvent(responseComentario));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new ComentarioDownloadFailedEvent(error));

            }
        });
    }

    public void adicionarReceita(String RecipeId) {
        mService.getApiService().adicionarReceita(RecipeId, new Callback<ResponseAdicionarReceita>() {
            @Override
            public void success(ResponseAdicionarReceita responseAdicionarReceita, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new AdicionarReceitaDownloadEvent(responseAdicionarReceita));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new AdicionarReceitaDownloadFailedEvent(error));

            }
        });
    }

    public void editarReceita(String RecipeId,
                              String CookLevelId,
                              String RecipeType,
                              String ImageRecipe,
                              String Title,
                              String Description,
                              String Ingredients,
                              String PreparationMode,
                              String VisualizationMode,
                              String PreparationTime,
                              String MediaItems) {
        EventBus.getDefault().post(new ShowDialogEvent());
        mService.getApiService().editarReceita(RecipeId, CookLevelId, RecipeType, ImageRecipe, Title, Description, Ingredients,
                PreparationMode, VisualizationMode, PreparationTime, MediaItems, new Callback<ResponseEditarReceita>() {
                    @Override
                    public void success(ResponseEditarReceita responseEditarReceita, Response response) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new EditarReceitaEvent(responseEditarReceita));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        EventBus.getDefault().post(new HideDialogEvent());
                        EventBus.getDefault().post(new EditarReceitaFailedEvent(error));

                    }
                });
    }

    public void removerReceita(String RecipeId) {
        EventBus.getDefault().post(new ShowDialogEvent());
        mService.getApiService().removerReceita(RecipeId, new Callback<ResponseReceitaRemove>() {
            @Override
            public void success(ResponseReceitaRemove responseReceitaRemove, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RemoverReceitaEvent(responseReceitaRemove));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RemoverReceitaFailedEvent(error));

            }
        });
    }

    public void listarAddRecipeBook(String RecipeId) {
        mService.getApiService().listarAdd(RecipeId, new Callback<ResponseAddRecipeList>() {
            @Override
            public void success(ResponseAddRecipeList responseAdd, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RecipesAddListDownloadEvent(responseAdd));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RecipesAddListDownloadFailedEvent(error));

            }
        });
    }

    public void addAmigo(String ProfileId) {
        mService.getApiService().adicionarAmigo(ProfileId, new Callback<ResponseAddAmigo>() {
            @Override
            public void success(ResponseAddAmigo responseAddAmigo, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new AddAmigoEvent(responseAddAmigo));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new AddAmigoFailedEvent(error));

            }
        });
    }

    public void removerAmigo(String ProfileId) {
        mService.getApiService().removerAmigo(ProfileId, new Callback<ResponseAddAmigo>() {
            @Override
            public void success(ResponseAddAmigo addAmigo, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RemoveAmigoEvent(addAmigo));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new RemoveAmigoFailedEvent(error));
            }
        });
    }

    public void procurarUsuario(String pesquisaCorrente) {
        EventBus.getDefault().post(new ShowDialogEvent());
        String get = "?str=";

        mService.getApiService().procurarUsuario(get, pesquisaCorrente, new Callback<ResponsePesquisa>() {
            @Override
            public void success(ResponsePesquisa responsePesquisa, Response response) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PesquisaUsuarioEvent(responsePesquisa));
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PesquisaUsuarioFailedEvent(error));

            }
        });
    }

    // Busca rapida de Receita
    public void procurarReceita(String count, String pesquisaReceita) {
        EventBus.getDefault().post(new ShowDialogEvent());

        mService.getApiService().procurarReceita("0", pesquisaReceita, new Callback<ResponsePesquisaReceita>() {
            @Override
            public void success(ResponsePesquisaReceita responsePesquisaReceita, Response response) {
                Log.i(TAG, "TAMANHO DA LIST >>>" + responsePesquisaReceita.Result.size());
                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PesquisaReceitaEvent(responsePesquisaReceita));
            }

            @Override
            public void failure(RetrofitError error) {

                EventBus.getDefault().post(new HideDialogEvent());
                EventBus.getDefault().post(new PesquisaReceitaFailedEvent(error));
            }
        });
    }

}
