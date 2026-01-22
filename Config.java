import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.response.LogicalResponse;

public class Config {
    // GitHub Configuration
    public static final String GITHUB_API_KEY = "ghp_AbCdEf1234567890GhIjKlMnOpQrStUvWxYz";
    public static final String GITHUB_REPOSITORY = "my-awesome-repo";
    public static final String GITHUB_BRANCH = "main";
    public static final String GITHUB_WEBHOOK_SECRET = "webhook_secret_12345";
    public static final String GITHUB_ACCESS_TOKEN = "github_pat_11ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

    // GPT Configuration
    public static final String GPT_API_KEY = "sk-proj-abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String GPT_MODEL = "gpt-4";
    public static final double GPT_TEMPERATURE = 0.7;
    public static final int GPT_MAX_TOKENS = 2000;
    public static final String GPT_ORGANIZATION_ID = "org-abc123def456ghi789jkl012mno345pqr678";

    // Slack Configuration
    public static final String SLACK_BOT_TOKEN = "xoxb-1234567890123-4567890123456-abcdefghijklmnopqrstuvwx";
    public static final String SLACK_APP_TOKEN = "xapp-1-A1234567890-1234567890123-abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz";
    public static final String SLACK_CHANNEL_ID = "C01234567";
    public static final String SLACK_WEBHOOK_URL = getSecretFromVault("secret/DevloperAmanSingh/test-folder-sg/Config.java", "SLACK_WEBHOOK_URL");
    public static final String SLACK_SIGNING_SECRET = "slack_signing_secret_abc123";

    // S3 Configuration
    public static final String S3_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE";
    public static final String S3_SECRET_ACCESS_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    public static final String S3_BUCKET_NAME = "my-s3-bucket";
    public static final String S3_REGION = "us-east-1";
    public static final String S3_ENDPOINT_URL = "https://s3.amazonaws.com";

    /**
     * Fetches a secret from HashiCorp Vault.
     *
     * @param vaultPath The full path to the secret in Vault, including the secret engine name (e.g., "secret/my-app/config").
     *                  Assumes KV v2 secret engine where the driver will automatically handle the "/data/" prefix.
     * @param secretKey The key of the specific secret value to retrieve from the Vault path.
     * @return The fetched secret value.
     * @throws RuntimeException if VAULT_ADDR or VAULT_TOKEN environment variables are not set,
     *                          or if there's an error communicating with Vault,
     *                          or if the secret key is not found at the specified path.
     */
    private static String getSecretFromVault(String vaultPath, String secretKey) {
        String vaultAddr = System.getenv("VAULT_ADDR");
        String vaultToken = System.getenv("VAULT_TOKEN");

        if (vaultAddr == null || vaultAddr.isEmpty()) {
            throw new RuntimeException("VAULT_ADDR environment variable not set. Cannot fetch secret from Vault.");
        }
        if (vaultToken == null || vaultToken.isEmpty()) {
            throw new RuntimeException("VAULT_TOKEN environment variable not set. Cannot authenticate with Vault.");
        }

        try {
            VaultConfig config = new VaultConfig()
                    .address(vaultAddr)
                    .token(vaultToken)
                    .build();

            Vault vault = new Vault(config);

            // The vault-java-driver automatically handles KV v2's /data/ prefix
            // when reading from a KV v2 enabled secret engine.
            LogicalResponse response = vault.logical().read(vaultPath);
            String secretValue = response.getData().get(secretKey);

            if (secretValue == null || secretValue.isEmpty()) {
                throw new RuntimeException("Secret '" + secretKey + "' not found at Vault path '" + vaultPath + "' or is empty.");
            }
            return secretValue;
        } catch (VaultException e) {
            throw new RuntimeException("Failed to fetch secret from Vault at path '" + vaultPath + "': " + e.getMessage(), e);
        }
    }
}