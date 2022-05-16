import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.mycompany.fusionauth.plugins.ExamplePBDKF2HMACSHA1PasswordEncryptor;
import io.fusionauth.plugin.spi.PluginModule;
import io.fusionauth.plugin.spi.security.PasswordEncryptor;

@PluginModule
public class MyExampleFusionAuthPluginModule extends AbstractModule {
  @Override
  protected void configure() {
    MapBinder<String, PasswordEncryptor> passwordEncryptorMapBinder = MapBinder.newMapBinder(binder(), String.class, PasswordEncryptor.class);
    passwordEncryptorMapBinder.addBinding("example-salted-pbkdf2-hmac-sha1-10000").to(ExamplePBDKF2HMACSHA1PasswordEncryptor.class);
  }
}
