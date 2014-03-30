CFS
===

Implementation of the Completely Fair Schedular algorithm used in the Linux Kernel.

The algorithm stores the job in an RBTree sorted by the time spend by the job while being active inside the processor.

Take a look at this [Linux CFS Document](https://github.com/torvalds/linux/blob/master/Documentation/scheduler/sched-design-CFS.txt) for reference.

For any additions and fixes, leave an issue and then a pull request. I am opened to accepting those which I find reasonable.
