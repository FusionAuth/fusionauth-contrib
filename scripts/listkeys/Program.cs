using io.fusionauth;
using io.fusionauth.converters.helpers;
using io.fusionauth.domain;
using io.fusionauth.domain.provider;
using System.Text;

namespace DumpCertificatesInFusionAuthInstance
{
    class DumpCertificateUsage
    {

        static void Main()
        {
                string url = "YOUR_URL";
                string apiKey = "YOUR_API_KEY";
                TextWriter textWriter = Console.Out;
                DumpCertificateUsage dcu = new DumpCertificateUsage(url, apiKey, textWriter);
                dcu.DumpAllCertificates();
        }

        private readonly string _url;
        private readonly string _apiKey;
        private readonly FusionAuthSyncClient _client;
        private readonly Dictionary<Guid, Key> _keys;
        private readonly TextWriter _writer;

        public DumpCertificateUsage(string url, string apiKey, TextWriter textWriter) 
        { 
            _url = url;
            _apiKey = apiKey;
            _client = new FusionAuthSyncClient(apiKey, url);
            _keys = new Dictionary<Guid, Key>();
            _writer = textWriter;
        }

        private static string DictionaryOfErrorDataToString(IDictionary<string, object> errorData)
        {
            if (errorData == null) return string.Empty;
            var errorMsg = new StringBuilder();
            foreach (var key in errorData.Keys)
            {
                errorMsg.AppendLine($"{key}={errorData[key]}");
            }

            return errorMsg.ToString();
        }

        private static void ThrowExceptionFromClientResponse<T>(ClientResponse<T> response, string message)
        {
            if (!response.WasSuccessful())
            {
                if (response.exception != null) throw new Exception($"failed: {message}", response.exception);

                if (response.statusCode == 404) throw new Exception($"failed: {message}. Not Found");
                if (response.statusCode == 401) throw new Exception($"failed: {message}. Not Authorized");

                var msgBuilder = new StringBuilder(message);
                foreach (var general in response.errorResponse.generalErrors)
                {
                    msgBuilder.AppendLine($"{general.message}: ({general.code}) {DictionaryOfErrorDataToString(general.data)}");
                }
                foreach (var fieldData in response.errorResponse.fieldErrors)
                {
                    msgBuilder.Append($"{fieldData.Key} ");
                    foreach (var fieldError in fieldData.Value)
                    {
                        msgBuilder.AppendLine($"{fieldError.message}: ({fieldError.code}) {DictionaryOfErrorDataToString(fieldError.data)}");
                    }
                }

                throw new Exception(msgBuilder.ToString());
            }
        }

        public void DumpApplication(FusionAuthSyncClient tenantClient, Application application)
        {
            _writer.WriteLine($"  Application: {application.name}");
            if (application.jwtConfiguration != null)
            {
                if (application.jwtConfiguration.accessTokenKeyId != null)
                {
                    if (_keys.TryGetValue(application.jwtConfiguration.accessTokenKeyId.Value, out Key? key) && key != null)
                    {
                        _writer.WriteLine($"    jwtConfiguration.accessTokenKeyId: {key.name} ({key.issuer})");
                    }
                }
                if (application.jwtConfiguration.idTokenKeyId != null)
                {
                    if (_keys.TryGetValue(application.jwtConfiguration.idTokenKeyId.Value, out Key? key) && key != null)
                    {
                        _writer.WriteLine($"    jwtConfiguration.idTokenKeyId: {key.name} ({key.issuer})");
                    }
                }
            }
            if (application.samlv2Configuration != null) {
                if (application.samlv2Configuration.defaultVerificationKeyId != null)
                {
                    if (_keys.TryGetValue(application.samlv2Configuration.defaultVerificationKeyId.Value, out Key? key) && key != null)
                    {
                        _writer.WriteLine($"    samlv2Configuration.defaultVerificationKeyId: {key.name} ({key.issuer})");
                    }
                }
            }
        }

        public void DumpApplications(FusionAuthSyncClient tenantClient, IEnumerable<Application> applications)
        {
            foreach(var application in applications)
            {
                DumpApplication(tenantClient, application);
            }
        }

        public void DumpIdentityProvider(FusionAuthSyncClient tenantClient, IdentityProvider identityProvider)
        {
            var idp = identityProvider as SAMLv2IdPInitiatedIdentityProvider;
            if (idp != null)
            {
                if(idp.keyId != null)
                {
                    if (_keys.TryGetValue(idp.keyId.Value, out Key? key) && key != null)
                    {
                        _writer.WriteLine($"    {idp.name} idp.keyId: {key.name} ({key.issuer})");
                    }
                }
            }
        }

        public void DumpIdentityProviders(FusionAuthSyncClient tenantClient, IEnumerable<IdentityProvider> identityProviders)
        {
            foreach (var identityProvider in identityProviders)
            {
                DumpIdentityProvider(tenantClient, identityProvider);
            }
        }

        public void DumpTenant(Tenant tenant)
        {
            _writer.WriteLine($"Tenant: {tenant.name}");

            if(tenant.jwtConfiguration != null)
            {
                if (tenant.jwtConfiguration.accessTokenKeyId != null)
                {
                    if (_keys.TryGetValue(tenant.jwtConfiguration.accessTokenKeyId.Value, out Key? key) && key != null)
                    {
                        _writer.WriteLine($"  jwtConfiguration.accessTokenKeyId: {key.name} ({key.issuer})");
                    }
                }
                if (tenant.jwtConfiguration.idTokenKeyId != null)
                {
                    if (_keys.TryGetValue(tenant.jwtConfiguration.idTokenKeyId.Value, out Key? key) && key != null)
                    {
                        _writer.WriteLine($"  jwtConfiguration.idTokenKeyId: {key.name} ({key.issuer})");
                    }
                }
            }

            // applications
            var tenantClient = new FusionAuthSyncClient(_apiKey, _url, tenant.id.ToString());
            var applicationResponse = tenantClient.RetrieveApplications();
            if (applicationResponse != null)
            {
                ThrowExceptionFromClientResponse(applicationResponse, $"getting all applications for {tenant.name}");
                if (applicationResponse.successResponse != null)
                {
                    DumpApplications(tenantClient, applicationResponse.successResponse.applications);
                }
            }

            // identityProviders
            var idpResponse = tenantClient.RetrieveIdentityProviders();
            if (idpResponse != null)
            {
                ThrowExceptionFromClientResponse(idpResponse, $"getting all identityproviders for {tenant.name}");
                if (idpResponse.successResponse != null)
                {
                    DumpIdentityProviders(tenantClient, idpResponse.successResponse.identityProviders);
                }
            }
        }

        public void DumpTenants(IEnumerable<Tenant> tenants)
        {
            foreach (var tenant in tenants)
            {
                DumpTenant(tenant);
            }
        }

        public void DumpAllCertificates()
        {
            var keysResponse = _client.RetrieveKeys();
            ThrowExceptionFromClientResponse(keysResponse, "getting all keys");
            foreach (var key in keysResponse.successResponse.keys)
            {
                if (key.id.HasValue)
                {
                    _keys.Add(key.id.Value, key);
                }
            }

            var tenantResponse = _client.RetrieveTenants();
            ThrowExceptionFromClientResponse(tenantResponse, "getting all tenants");
            DumpTenants(tenantResponse.successResponse.tenants);
        }
    }

}
