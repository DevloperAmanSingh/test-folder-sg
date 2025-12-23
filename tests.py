import hvac

client = hvac.Client()
vault_path = 'secret/data/DevloperAmanSingh/test-folder-sg/tests.py'
secret_key = 'secret'
read_response = client.secrets.kv.v2.read_secret_version(path=vault_path)
GITHUB_TOKEN = read_response['data']['data'][secret_key]