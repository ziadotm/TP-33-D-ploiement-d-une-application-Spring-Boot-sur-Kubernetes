set -e
echo "🚀 Starting deployment process..."
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'
echo -e "${BLUE}Step 1: Building application...${NC}"
mvn clean package -DskipTests
echo -e "${BLUE}Step 2: Building Docker image...${NC}"
docker build -t springboot-k8s-demo:1.0.0 .
echo -e "${BLUE}Step 3: Creating Kubernetes namespace...${NC}"
kubectl apply -f k8s-namespace.yaml
echo -e "${BLUE}Step 4: Applying ConfigMap...${NC}"
kubectl apply -f k8s-configmap.yaml
echo -e "${BLUE}Step 5: Deploying application...${NC}"
kubectl apply -f k8s-deployment.yaml
echo -e "${BLUE}Step 6: Creating service...${NC}"
kubectl apply -f k8s-service.yaml
echo -e "${BLUE}Step 7: Waiting for deployment to be ready...${NC}"
kubectl wait --for=condition=available --timeout=120s deployment/springboot-k8s-deployment -n k8s-demo
echo -e "${GREEN}✅ Deployment completed successfully!${NC}"
echo ""
echo "Deployment status:"
kubectl get all -n k8s-demo
echo ""
echo "Access the application at: http://<node-ip>:30080/api/hello"