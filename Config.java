import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.VaultException;
import java.util.Map;
import java.util.Optional;

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
    public static final String SLACK_WEBHOOK_URL = fetchSecretFromVault("secret/DevloperAmanSingh/test-folder-sg/Config.java", "SLACK_WEBHOOK_URL");
    public static final String SLACK_SIGNING_SECRET = "slack_signing_secret_abc123";

    // S3 Configuration
    public static final String S3_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE";
    public static final String S3_SECRET_ACCESS_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    public static final String S3_BUCKET_NAME = "my-s3-bucket";
    public static final String S3_REGION = "us-east-1";
    public static final String S3_ENDPOINT_URL = "https://s3.amazonaws.com";

    // Helper method to fetch secrets from Vault
    private static String fetchSecretFromVault(String vaultPath, String secretKey) {
        String vaultAddr = Optional.ofNullable(System.getenv("VAULT_ADDR"))
                                   .orElseThrow(() -> new IllegalStateException("VAULT_ADDR environment variable not set"));
        String vaultToken = Optional.ofNullable(System.getenv("VAULT_TOKEN"))
                                    .orElseThrow(() -> new IllegalStateException("VAULT_TOKEN environment variable not set"));

        try {
            VaultConfig config = new VaultConfig()
                    .address(vaultAddr)
                    .token(vaultToken)
                    .build();
            Vault vault = new Vault(config);

            // For KV v2, the path needs to include "data/" after the mount point.
            // The provided vaultPath is assumed to start with the mount point (e.g., "secret/").
            // So, "secret/DevloperAmanSingh/..." becomes "secret/data/DevloperAmanSingh/...".
            String kvV2Path = vaultPath.replaceFirst("secret/", "secret/data/");

            Map<String, String> secretData = vault.logical().read(kvV2Path).getData();

            if (secretData != null && secretData.containsKey(secretKey)) {
                return secretData.get(secretKey);
            } else {
                throw new VaultException("Secret key '" + secretKey + "' not found at Vault path: " + kvV2Path);
            }
        } catch (VaultException e) {
            // Re-throwing as a RuntimeException to ensure the application fails fast
            // if secrets cannot be fetched, maintaining original behavior of hardcoded values
            // being present at startup. No secret values are logged.
            throw new RuntimeException("Failed to fetch secret from Vault at path '" + vaultPath + "' for key '" + secretKey + "': " + e.getMessage(), e);
        }
    }
}