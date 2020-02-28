#include<iostream>
#include<vector>
#include<math.h>
#include<algorithm>
using namespace std;

vector<vector<int>> res;
vector<int> path;

void search(vector<int> nums, int start) {
    for (int i = start; i < nums.size(); i++) {
        //cout << path.size() - 1 << endl;
        if (i > 0 && nums[i] == nums[i - 1] && nums[i] != path[path.size() - 1]) {
            continue;//
        }
        cout<<"Github<<endl;
        path.push_back(nums[i]);
        res.push_back(path);
        search(nums, i + 1);
        path.pop_back();
    }
}

vector<vector<int>> subsetsWithDup(vector<int>& nums) {
    sort(nums.begin(), nums.end());
    res.push_back(path);
    search(nums, 0);
    return res;
}

int main()
{
    int res =0;
    res=min(2,min(3,4));
    cout<<res;
    return 0;
}
