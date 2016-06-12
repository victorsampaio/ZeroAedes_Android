package com.br.zeroaedes;


import com.br.zeroaedes.view.RegisterFocusActivity;

import javax.inject.Singleton;

import br.com.prolins.niceCook.util.TextUtil;
import br.com.prolins.niceCook.view.Activities.CadastroActivity;
import br.com.prolins.niceCook.view.Activities.LoginActivity;
import br.com.prolins.niceCook.view.Activities.RecuperarSenhaActivity;
import br.com.prolins.niceCook.view.Activities.SlidePaneActivity;
import br.com.prolins.niceCook.view.Activities.TermosActivity;
import br.com.prolins.niceCook.view.Fragments.AboutAmigoFragment;
import br.com.prolins.niceCook.view.Fragments.AboutFragment;
import br.com.prolins.niceCook.view.Fragments.AlterarSenhaFragment;
import br.com.prolins.niceCook.view.Fragments.AmigosFragment;
import br.com.prolins.niceCook.view.Fragments.CadastrarGrupoFragment;
import br.com.prolins.niceCook.view.Fragments.ComentariosFragment;
import br.com.prolins.niceCook.view.Fragments.ConvidarAmigosFragment;
import br.com.prolins.niceCook.view.Fragments.CurtidasFragment;
import br.com.prolins.niceCook.view.Fragments.EditarPerfilFragment;
import br.com.prolins.niceCook.view.Fragments.EditarReceitaFragment;
import br.com.prolins.niceCook.view.Fragments.FeedAmigosFragment;
import br.com.prolins.niceCook.view.Fragments.FeedFragment;
import br.com.prolins.niceCook.view.Fragments.FeedLivroAmigoFragment;
import br.com.prolins.niceCook.view.Fragments.FotosFragment;
import br.com.prolins.niceCook.view.Fragments.GruposFragment;
import br.com.prolins.niceCook.view.Fragments.NotificationFragment;
import br.com.prolins.niceCook.view.Fragments.PerfilAmigoFragment;
import br.com.prolins.niceCook.view.Fragments.ReceitaDetalhesFragment;
import br.com.prolins.niceCook.view.Fragments.ReceitasFragment;
import br.com.prolins.niceCook.view.Fragments.LoginFragment;
import br.com.prolins.niceCook.view.Fragments.NavigationDrawerFragment;
import br.com.prolins.niceCook.view.Fragments.PerfilFragment;
import br.com.prolins.niceCook.view.Fragments.CadastrarReceitaFragment;
import br.com.prolins.niceCook.view.Fragments.RecipeNotificationFragment;
import br.com.prolins.niceCook.view.Fragments.SeguindoFragment;
import br.com.prolins.niceCook.view.Fragments.VideosFragment;
import br.com.prolins.niceCook.wsconsumer.ContainerManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Tiago Alencar on 06/01/16.
 */
@Module(library = true, injects = {
        RegisterFocusActivity.class,
        //CadastrarReceitaFragment.class,
        //PerfilFragment.class,

}, complete = false)
public class ContainerInject {

    private CustomApplication mCustomApplication;
    /*private EventosManager mEventosManager;
    private AtividadeManager mAtividadeManager;
    private ComentarioManager mComentarioManager;
    private SeguidoresManager mSeguidorManager;
    private SeguindoManager mSeguindoManager;
    private CurtidaManager mCurtidaManager;*/

    public ContainerInject(CustomApplication customApplication) {
        mCustomApplication = customApplication;
        providerContainerManager();

    }

    @Provides
    public ContainerManager providerContainerManager() {
        return new ContainerManager(/*providerEventosManager(),*/ mCustomApplication.getApplicationContext()/*,
                providerAtividadeManager(),
                providerComentarioManager(),
                providerSeguidoresManager(),
                providerSeguindoManager(),
                providerCurtidadManager()*/);
    }

   /* @Provides
    @Singleton
    public EventosManager providerEventosManager() {
        mEventosManager = new EventosManager();
        return mEventosManager;
    }

    @Provides
    @Singleton
    public CurtidaManager providerCurtidadManager() {
        mCurtidaManager = new CurtidaManager();
        return mCurtidaManager;
    }

    @Provides
    @Singleton
    public SeguidoresManager providerSeguidoresManager() {
        mSeguidorManager = new SeguidoresManager();
        return mSeguidorManager;
    }

    @Provides
    @Singleton
    public SeguindoManager providerSeguindoManager() {
        mSeguindoManager = new SeguindoManager();
        return mSeguindoManager;
    }

    @Provides
    @Singleton
    public ComentarioManager providerComentarioManager() {
        mComentarioManager = new ComentarioManager();
        return mComentarioManager;
    }

    @Provides
    @Singleton
    public AtividadeManager providerAtividadeManager() {
        mAtividadeManager = new AtividadeManager();
        return new AtividadeManager();
    }*/

    @Provides
    @Singleton
    public TextUtil providerTextUtil() {
        return new TextUtil();
    }

}
