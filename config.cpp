#include <string>

namespace Config {
    // GitHub Configuration
    const std::string GITHUB_API_KEY = "ghp_xxxxxxxxxxxxxxxxxxxx";
    const std::string GITHUB_REPOSITORY = "my-awesome-repo";
    const std::string GITHUB_BRANCH = "main";
    const std::string GITHUB_WEBHOOK_SECRET = "webhook_secret_12345";
    const std::string GITHUB_ACCESS_TOKEN = "github_pat_xxxxxxxx";
    
    // GPT Configuration
    const std::string GPT_API_KEY = "sk-xxxxxxxxxxxxxxxxxxxxxxxx";
    const std::string GPT_MODEL = "gpt-4";
    const double GPT_TEMPERATURE = 0.7;
    const int GPT_MAX_TOKENS = 2000;
    const std::string GPT_ORGANIZATION_ID = "org-xxxxxxxx";
    
    // Slack Configuration
    const std::string SLACK_BOT_TOKEN = "xoxb-xxxxxxxxxxxx-xxxxxxxxxxxx";
    const std::string SLACK_APP_TOKEN = "xapp-xxxxxxxxxxxx";
    const std::string SLACK_CHANNEL_ID = "C01234567";
    const std::string SLACK_WEBHOOK_URL = "https://hooks.slack.com/services/xxx/xxx/xxx";
    const std::string SLACK_SIGNING_SECRET = "slack_signing_secret_abc123";
    
    // S3 Configuration
    const std::string S3_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE";
    const std::string S3_SECRET_ACCESS_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
    const std::string S3_BUCKET_NAME = "my-s3-bucket";
    const std::string S3_REGION = "us-east-1";
    const std::string S3_ENDPOINT_URL = "https://s3.amazonaws.com";
}

