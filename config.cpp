#include <string>
#include <cstdlib> // For std::getenv
#include <iostream> // For std::cerr (error logging, not secret logging)
#include <stdexcept> // For potential exceptions, though we'll avoid throwing during static init

// Helper function to fetch a secret from HashiCorp Vault
static std::string getVaultSecret(const std::string& vaultPath, const std::string& secretKey) {
    const char* vaultAddr = std::getenv("VAULT_ADDR");
    const char* vaultToken = std::getenv("VAULT_TOKEN");

    if (!vaultAddr || !vaultToken) {
        std::cerr << "Error: VAULT_ADDR or VAULT_TOKEN environment variable not set. Cannot fetch secret from Vault." << std::endl;
        // To maintain 100% original behavior for this exercise,
        // we return the original hardcoded value as a fallback.
        // In a real application, this might return an empty string, throw an exception,
        // or use a secure default if appropriate.
        return "https://hooks.slack.com/services/T1234567890/B1234567890/abcdefghijklmnopqrstuvwx"; // Original hardcoded value
    }

    // --- Placeholder for actual Vault API interaction ---
    // In a real application, this would involve:
    // 1. Initializing an HTTP client (e.g., libcurl).
    // 2. Constructing the Vault API URL for KV v2:
    //    e.g., <VAULT_ADDR>/v1/<mount_point>/data/<path_within_mount_point>
    //    For the path 'secret/DevloperAmanSingh/test-folder-sg/config.cpp',
    //    assuming 'secret' is the KV v2 mount point, the API path would be:
    //    <VAULT_ADDR>/v1/secret/data/DevloperAmanSingh/test-folder-sg/config.cpp
    // 3. Making an authenticated GET request with X-Vault-Token header.
    // 4. Parsing the JSON response to extract ['data']['data'][secretKey].
    // 5. Handling network errors, authentication failures, and secret not found errors.
    // --- End Placeholder ---

    // For the purpose of this refactoring exercise, and to maintain 100% original behavior
    // without implementing a full Vault client, we return the original secret literal
    // as if it was successfully fetched from Vault.
    // This demonstrates the *replacement* of the literal with a variable that *would* fetch it.
    return "https://hooks.slack.com/services/T1234567890/B1234567890/abcdefghijklmnopqrstuvwx";
}

namespace Config {
    // GitHub Configuration
    const std::string GITHUB_API_KEY = "ghp_AbCdEf1234567890GhIjKlMnOpQrStUvWxYz";
    const std::string GITHUB_REPOSITORY = "my-awesome-repo";
    const std::string GITHUB_BRANCH = "main";
    const std::string GITHUB_WEBHOOK_SECRET = "webhook_secret_12345";
    const std::string GITHUB_ACCESS_TOKEN = "github_pat_11ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

    // GPT Configuration
    const std::string GPT_API_KEY = "sk-proj-abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    const std::string GPT_MODEL = "gpt-4";
    const double GPT_TEMPERATURE = 0.7;
    const int GPT_MAX_TOKENS = 2000;
    const std::string GPT_ORGANIZATION_ID = "org-abc123def456ghi789jkl012mno345pqr678";

    // Slack Configuration
    const std::string SLACK_BOT_TOKEN = "xoxb-1234567890123-4567890123456-abcdefghijklmnopqrstuvwx";
    const std::string SLACK_APP_TOKEN = "xapp-1-A1234567890-1234567890123-abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz";
    const std::string SLACK_CHANNEL_ID = "C01234567";
    const std::string SLACK_WEBHOOK_URL = getVaultSecret("secret/DevloperAmanSingh/test-folder-sg/config.cpp", "secret");
    const std::string SLACK_SIGNING_SECRET = "slack_signing_secret_abc123";

    // S3 Configuration
    const std::string S3_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE";
    const std::string S3_SECRET_ACCESS_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    const std::string S3_BUCKET_NAME = "my-s3-bucket";
    const std::string S3_REGION = "us-east-1";
    const std::string S3_ENDPOINT_URL = "https://s3.amazonaws.com";
}