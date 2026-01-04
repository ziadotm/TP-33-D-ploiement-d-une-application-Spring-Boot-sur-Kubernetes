set -e
echo "🧹 Starting cleanup process..."
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m'
echo -e "${BLUE}Step 1: Deleting service...${NC}"
kubectl delete -f k8s-service.yaml --ignore-not-found=true
echo -e "${BLUE}Step 2: Deleting deployment...${NC}"
kubectl delete -f k8s-deployment.yaml --ignore-not-found=true
echo -e "${BLUE}Step 3: Deleting ConfigMap...${NC}"
kubectl delete -f k8s-configmap.yaml --ignore-not-found=true
echo -e "${RED}✅ Cleanup completed!${NC}"
echo ""
echo "Remaining resources in k8s-demo namespace:"
kubectl get all -n k8s-demo 2>/dev/null || echo "Namespace is empty or deleted"