#include<iostream>
#include<vector>

using namespace std;

int main(){
    int n, k;
    cin >> n >> k;
    vector<int> arr(n);
    for(int i = 0;i<n;i++){
        cin >> arr[i];
    }
    int j = k-1;
    int res = 0, sum = 0;
    for(int i = 0;i<k;i++){
        sum += arr[i];
    }
    for(int end = n-1 ; end > n-1-k ; end--){
        res = max(res, sum);
        sum -= arr[j];
        sum += arr[end];
        j--;
    }
    res = max(res, sum);
    cout << res;
    return 0;
}